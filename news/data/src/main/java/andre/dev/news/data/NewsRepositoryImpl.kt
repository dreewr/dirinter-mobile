package andre.dev.news.data

import andre.dev.news.domain.NewsException
import andre.dev.news.domain.NewsRepository
import andre.dev.news.domain.model.Article
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val cacheSource: NewsCacheSource,
    private val remoteSource: NewsRemoteSource
) : NewsRepository {

    override suspend fun getArticles(startTimestamp: Long, pageSize: Int): List<Article> {

        suspend fun getRefreshedArticles(timestamp: Long, size: Int): List<Article> {
            remoteSource.fetchArticles(timestamp, size).also {
                cacheSource.insertAll(it)
            }
            return cacheSource.getArticles(timestamp, size)
        }

        val cachedArticles = cacheSource.getArticles(startTimestamp, pageSize)

        if (cachedArticles.size == pageSize) return cachedArticles

        val additionalArticles = try {
            getRefreshedArticles(
                cachedArticles.minOfOrNull { it.publishingTimestamp } ?: startTimestamp,
                pageSize - cachedArticles.size)
        } catch (e: Exception) {
            cachedArticles.ifEmpty { throw NewsException.NetworkError() }
        }

        return cachedArticles + additionalArticles
    }
}
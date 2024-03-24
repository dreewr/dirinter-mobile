package andre.dev.news.data

import andre.dev.news.domain.NewsRepository
import andre.dev.news.domain.model.Article
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val cacheSource: NewsCacheSource,
    private val remoteSource: NewsRemoteSource
) : NewsRepository {

    override suspend fun getArticles(startTimestamp: Long?, pageSize: Int): List<Article> {

        suspend fun getRefreshedArticles(startTimestamp: Long, size: Int) = run {

            cacheSource.insertAll(remoteSource.fetchArticles(startTimestamp, size))

            cacheSource.getArticles(startTimestamp, pageSize)
        }

        if (startTimestamp == null) return getRefreshedArticles(System.currentTimeMillis(), pageSize)

        cacheSource.getArticles(startTimestamp, pageSize).let { cachedArticles ->
            if (cachedArticles.size == pageSize) return cachedArticles

            return getRefreshedArticles(
                cachedArticles.minOfOrNull { it.timestamp } ?: startTimestamp,
                pageSize - cachedArticles.size
            )
        }
    }
}







package andre.dev.news.data

import andre.dev.news.domain.NewsException
import andre.dev.news.domain.NewsRepository
import andre.dev.news.domain.model.Article
import andre.dev.news.domain.model.ArticleSummary
import java.security.AuthProvider
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val cacheSource: NewsCacheSource,
    private val remoteSource: NewsRemoteSource
) : NewsRepository {

    override suspend fun getArticles(startTimestamp: Long, pageSize: Int): List<ArticleSummary> {

        suspend fun getRefreshedArticles(timestamp: Long, size: Int): List<ArticleSummary> {
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

        return (cachedArticles + additionalArticles).distinctBy { it.id }
    }

    override suspend fun getArticle(id: String): Article {

        cacheSource.getArticle(id)?.let { return it }

        return runCatching { remoteSource.getArticleById(id) }
            .onSuccess { freshArticle ->
                cacheSource.insertArticle(freshArticle)
                return freshArticle
            }.getOrElse { e ->
                println(e)
                throw NewsException.NetworkError()
            }
    }
}
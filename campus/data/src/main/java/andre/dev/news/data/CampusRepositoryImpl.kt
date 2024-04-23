package andre.dev.news.data

import andre.dev.campus.domain.CampusRepository
import javax.inject.Inject


class CampusRepositoryImpl @Inject constructor(
    private val cacheSource: CampusCacheSource,
    private val remoteSource: CampusRemoteSource
) : CampusRepository {

//    override suspend fun getArticles(startTimestamp: Long, pageSize: Int): List<ArticleSummary> {
//
//        suspend fun getRefreshedArticles(timestamp: Long, size: Int): List<ArticleSummary> {
//            remoteSource.fetchArticles(timestamp, size).also {
//                cacheSource.insertAll(it)
//            }
//            return cacheSource.getArticles(timestamp, size)
//        }
//
//        val cachedArticles = cacheSource.getArticles(startTimestamp, pageSize)
//
//        if (cachedArticles.size == pageSize) return cachedArticles
//
//        val additionalArticles = try {
//            getRefreshedArticles(
//                cachedArticles.minOfOrNull { it.publishingTimestamp } ?: startTimestamp,
//                pageSize - cachedArticles.size)
//        } catch (e: Exception) {
//            cachedArticles.ifEmpty { throw NewsException.NetworkError() }
//        }
//
//        return (cachedArticles + additionalArticles).distinctBy { it.id }
//    }

//    override suspend fun getArticle(id: String): Article {
//
//        cacheSource.getArticle(id)?.let { return it }
//
//        return runCatching { remoteSource.getArticleById(id) }
//            .onSuccess { freshArticle ->
//                cacheSource.insertArticle(freshArticle)
//                return freshArticle
//            }.getOrElse { e ->
//                println(e)
//                throw NewsException.NetworkError()
//            }
//    }
}
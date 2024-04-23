package andre.dev.news.cache

import andre.dev.news.data.CampusCacheSource
import javax.inject.Inject

class CampusCacheSourceImpl @Inject constructor(
    private val database: CampusDatabase
) : CampusCacheSource {

//    override suspend fun insertAll(news: List<ArticleSummary>) {
//        database.newsDao().insertAll(news.map { it.toEntity(System.currentTimeMillis()) })
//    }
//
//    override suspend fun getArticles(startTimestamp: Long, pageSize: Int): List<ArticleSummary> =
//        with(database.newsDao()) {
//            deleteExpiredArticleSummaries(System.currentTimeMillis())
//
//            getArticleSummaries(startTimestamp, pageSize)
//                .map { it.toArticle() }
//        }
//
//    override suspend fun insertArticle(article: Article) {
//        database.newsDao().insertArticle(article.toEntity(System.currentTimeMillis()))
//    }
//
//    override suspend fun getArticle(id: String): Article? = with(database.newsDao()) {
//        getArticleById(id, System.currentTimeMillis())?.toArticle()
//    }

}
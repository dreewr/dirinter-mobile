package andre.dev.news.cache

import andre.dev.news.data.LoginCacheSource
import javax.inject.Inject

class LoginCacheSourceImpl @Inject constructor(
    private val database: LoginDatabase
) : LoginCacheSource {

//    override suspend fun insertAll(news: List<ArticleSummary>) {
//        database.loginDao().insertAll(news.map { it.toEntity(System.currentTimeMillis()) })
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
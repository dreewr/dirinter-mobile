package andre.dev.news.data

import andre.dev.news.domain.model.Article
import andre.dev.news.domain.model.ArticleSummary

interface NewsCacheSource {
    suspend fun insertAll(news: List<ArticleSummary>)

    suspend fun getArticles(startTimestamp: Long, pageSize: Int): List<ArticleSummary>

    suspend fun insertArticle(article: Article)

    suspend fun getArticle(id: String): Article?

}
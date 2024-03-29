package andre.dev.news.data

import andre.dev.news.domain.model.ArticleSummary

interface NewsCacheSource {
    suspend fun insertAll(news: List<ArticleSummary>)

    suspend fun getArticles(startTimestamp: Long, pageSize: Int): List<ArticleSummary>

}
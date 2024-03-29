package andre.dev.news.data

import andre.dev.news.domain.model.ArticleSummary

interface NewsRemoteSource {

    suspend fun fetchArticles(startTimestamp: Long, pageSize: Int): List<ArticleSummary>

}
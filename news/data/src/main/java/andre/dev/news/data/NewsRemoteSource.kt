package andre.dev.news.data

import andre.dev.news.domain.model.Article

interface NewsRemoteSource{
    suspend fun fetchArticles(): List<Article>

    suspend fun fetchArticles(startTimestamp: Long, loadSize: Int): List<Article>

}
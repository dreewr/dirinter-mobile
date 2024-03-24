package andre.dev.news.data

import andre.dev.news.domain.model.Article

interface NewsCacheSource {
    suspend fun insertAll(news: List<Article>)

    suspend fun getArticles(startTimestamp: Long?, pageSize: Int): List<Article>

}
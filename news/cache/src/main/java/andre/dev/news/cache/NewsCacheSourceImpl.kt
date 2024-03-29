package andre.dev.news.cache

import andre.dev.news.data.NewsCacheSource
import andre.dev.news.domain.model.ArticleSummary
import javax.inject.Inject

class NewsCacheSourceImpl @Inject constructor(
    private val database: NewsDatabase
) : NewsCacheSource {

    override suspend fun insertAll(news: List<ArticleSummary>) {
        database.newsDao().insertAll(news.map { it.toEntity(System.currentTimeMillis()) })
    }

    override suspend fun getArticles(startTimestamp: Long, pageSize: Int): List<ArticleSummary> = with(database.newsDao()){
        deleteExpiredArticles(System.currentTimeMillis())

        getArticles(startTimestamp, pageSize)
            .map { it.toArticle() }
    }
}
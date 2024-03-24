package andre.dev.news.cache

import andre.dev.news.data.NewsCacheSource
import andre.dev.news.domain.model.Article
import javax.inject.Inject

class NewsCacheSourceImpl @Inject constructor() : NewsCacheSource {

    override suspend fun insertAll(news: List<Article>) {
        articles.apply {
            removeAll { article -> news.any { newArticle -> newArticle.id == article.id } }
            addAll(news)
        }
    }

    override suspend fun getArticles(startTimestamp: Long?, pageSize: Int): List<Article> =
        articles.filter { it.timestamp < (startTimestamp ?: 100) }
            .sortedByDescending { it.timestamp }
            .take(pageSize)

    companion object {
        val articles = ArrayList(List(20) { index ->
            val timestamp = 96 - index
            Article(
                id = timestamp.toString(),
                title = "CACHE with ID $timestamp",
                summary = "This is a summary of the news with ID $timestamp.",
                thumbnailUrl = "https://example.com/thumbnail/$timestamp.jpg",
                timestamp = timestamp.toLong()
            )
        })
    }
}
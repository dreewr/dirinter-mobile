package andre.dev.news.remote

import andre.dev.news.data.NewsRemoteSource
import andre.dev.news.domain.model.Article
import javax.inject.Inject

class NewsRemoteSourceImpl @Inject constructor(
    private val service: NewsService
): NewsRemoteSource {
    override suspend fun fetchArticles() = service.doSomething().articles
    override suspend fun fetchArticles(startTimestamp: Long, loadSize: Int) = List(loadSize) { index ->
        val timestamp = startTimestamp - 1 - index
        Article(
            id = timestamp.toString(),
            title = "News Title with ID $timestamp",
            summary = "This is a summary of the news with ID $timestamp.",
            thumbnailUrl = "https://example.com/thumbnail/$timestamp.jpg",
            timestamp = timestamp
        )
    }
}
package andre.dev.news.remote

import andre.dev.news.data.NewsRemoteSource
import andre.dev.news.domain.model.Article
import javax.inject.Inject

class NewsRemoteSourceImpl @Inject constructor(
    private val service: NewsService
) : NewsRemoteSource {
    override suspend fun fetchArticles() = service.doSomething().articles

    //Simulacro, emite noticias até o timestamp 80
    override suspend fun fetchArticles(startTimestamp: Long?, pageSize: Int) =
        if((((startTimestamp ?: 100).toInt() == 95)))
            List(1) { index ->
            val timestamp = ((startTimestamp?.minus(1)) ?: 100) - index
            Article(
                id = timestamp.toString(),
                title = "remote remote with ID $timestamp",
                summary = "This is a summary of the news with ID $timestamp.",
                thumbnailUrl = "https://example.com/thumbnail/$timestamp.jpg",
                timestamp = timestamp
            )
        }
        else if ((((startTimestamp ?: 100) < 95)))
            listOf()

        else
            List(pageSize) { index ->
            val timestamp = ((startTimestamp?.minus(1)) ?: 100) - index
            Article(
                id = timestamp.toString(),
                title = "remote remote with ID $timestamp",
                summary = "This is a summary of the news with ID $timestamp.",
                thumbnailUrl = "https://example.com/thumbnail/$timestamp.jpg",
                timestamp = timestamp
            )
        }
}
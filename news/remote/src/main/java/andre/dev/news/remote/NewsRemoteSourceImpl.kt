package andre.dev.news.remote

import andre.dev.news.data.NewsRemoteSource
import andre.dev.news.remote.response.toArticle
import javax.inject.Inject

class NewsRemoteSourceImpl @Inject constructor(
    private val service: NewsService
) : NewsRemoteSource {
    override suspend fun fetchArticles(startTimestamp: Long, pageSize: Int) =
        service.getArticles(startTimestamp, pageSize).articles.map { it.toArticle() }

}
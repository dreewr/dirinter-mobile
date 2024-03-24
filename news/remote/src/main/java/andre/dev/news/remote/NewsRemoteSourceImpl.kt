package andre.dev.news.remote

import andre.dev.news.data.NewsRemoteSource
import andre.dev.news.domain.model.Article
import javax.inject.Inject

class NewsRemoteSourceImpl @Inject constructor(
    private val service: NewsService
) : NewsRemoteSource {
    override suspend fun fetchArticles() = service.doSomething().articles
    override suspend fun fetchArticles(startTimestamp: Long?, pageSize: Int) = listOf<Article>()
}
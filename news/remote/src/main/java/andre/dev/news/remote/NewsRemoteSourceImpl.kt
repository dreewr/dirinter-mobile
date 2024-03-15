package andre.dev.news.remote

import andre.dev.news.data.NewsRemoteSource
import javax.inject.Inject

class NewsRemoteSourceImpl @Inject constructor(
    private val service: NewsService
): NewsRemoteSource {
    override suspend fun doSomething() = service.doSomething().articles

}
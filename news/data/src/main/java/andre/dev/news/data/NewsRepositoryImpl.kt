package andre.dev.news.data

import andre.dev.news.domain.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    val cacheSource: NewsCacheSource,
    val remoteSource: NewsRemoteSource
): NewsRepository {
    override fun doSomething() {
        TODO("Not yet implemented")
    }
}
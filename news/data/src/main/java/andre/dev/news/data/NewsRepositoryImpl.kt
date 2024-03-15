package andre.dev.news.data

import andre.dev.news.domain.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val cacheSource: NewsCacheSource,
    private val remoteSource: NewsRemoteSource
): NewsRepository {
    override suspend fun doSomething() = remoteSource.doSomething()

}
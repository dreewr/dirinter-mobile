package andre.dev.news.data

import andre.dev.news.domain.NewsRepository
import andre.dev.news.domain.model.Article
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val cacheSource: NewsCacheSource,
    private val remoteSource: NewsRemoteSource
) : NewsRepository {

    // Assuming this fetches the initial set of articles
    override suspend fun getArticles(): List<Article>{







        return remoteSource.fetchArticles()
    }



    companion object {
        const val PAGE_SIZE = 5 // Assuming a page consists of 5 articles
    }
}







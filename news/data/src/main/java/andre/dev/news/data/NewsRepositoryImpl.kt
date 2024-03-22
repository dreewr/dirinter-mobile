package andre.dev.news.data

import andre.dev.news.domain.NewsRepository
import andre.dev.news.domain.model.Article
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val cacheSource: NewsCacheSource,
    private val remoteSource: NewsRemoteSource
): NewsRepository {
    override suspend fun getArticles(): List<Article> =
        remoteSource.fetchArticles()

    suspend fun getOtherArticles(refresh: Boolean){

        val mostRecentTimestamp = cacheSource.getMostRecentTimestamp() ?: 0


        val newNews = remoteSource.fetchArticles(

            mostRecentTimestamp

        ).let { articles ->

            //aqui tem que fazer um mecanismo pra vir o tamanho da pagina

            // ex: tem 3 noticias mais novas
            if (articles.isEmpty()) {
                cacheSource.insertAll(listOf()) //ignore the listOf()
            } else {
                //cacheSource.
            }
        }
//
//        if (newNews.isNotEmpty()) {
//            // If new news is fetched from the API, convert and insert them into the local cache
//            newsDao.insertAll(newNews.map { it.toEntity() })
//        } else {
//            // If no new news is fetched from the API, fetch older news from the local cache
//            // This could be fetching news just before the most recent timestamp in the cache
//            val fallbackNews = fetchFromCacheBeforeTimestamp(mostRecentTimestamp)
//            // No need to insert these into the database as they are already there
//            // This step is more about deciding what to do next, e.g., updating a state to trigger the UI to show these news
//            // Consider how you would use these fallback news. For example, you might want to signal to the UI that these are the news to be displayed
//        }
    }
}





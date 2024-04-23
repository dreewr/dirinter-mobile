package andre.dev.campus.remote

import andre.dev.news.data.CampusRemoteSource
import javax.inject.Inject

class CampusRemoteSourceImpl @Inject constructor(
    private val service: CampusService
) : CampusRemoteSource {
//    override suspend fun fetchArticles(startTimestamp: Long, pageSize: Int) =
//        service.getArticles(startTimestamp, pageSize).articles.map { it.toArticle() }

    //override suspend fun getArticleById(id: String): Article = service.getArticle(id).toArticle()
}
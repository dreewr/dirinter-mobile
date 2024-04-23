package andre.dev.news.remote

import andre.dev.news.domain.model.Article
import andre.dev.news.remote.response.ArticleResponse
import andre.dev.news.remote.response.ArticleSummariesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {
    @GET("news")
    suspend fun getArticles(
        @Query("startTimestamp") startTimestamp: Long,
        @Query("pageSize") pageSize:Int
    ): ArticleSummariesResponse

    @GET("news/{id}")
    suspend fun getArticle(@Path("id") id: String): ArticleResponse
}
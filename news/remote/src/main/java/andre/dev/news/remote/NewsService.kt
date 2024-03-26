package andre.dev.news.remote

import andre.dev.news.remote.response.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("news")
    suspend fun getArticles(
        @Query("startTimestamp") startTimestamp: Long,
        @Query("pageSize") pageSize:Int
    ): ArticlesResponse
}
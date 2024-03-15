package andre.dev.news.remote

import andre.dev.news.remote.response.ArticlesResponse
import retrofit2.http.GET

interface NewsService {
    @GET("news")
    suspend fun doSomething(): ArticlesResponse

}
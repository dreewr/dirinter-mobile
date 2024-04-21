package andre.dev.news.remote

import andre.dev.news.remote.model.LoginRequest
import andre.dev.news.remote.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LoginService {
    @POST("login")
    suspend fun executeLogin(@Body request: LoginRequest): UserResponse
}
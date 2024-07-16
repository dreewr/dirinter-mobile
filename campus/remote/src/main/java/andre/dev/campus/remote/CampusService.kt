package andre.dev.campus.remote

import andre.dev.campus.remote.response.CampiResponse
import andre.dev.campus.remote.response.CampusResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CampusService {
    @GET("campus")
    suspend fun getCampi(): CampiResponse

    @GET("campus/{id}")
    suspend fun getCampus(@Path("id") id: String): CampusResponse
}
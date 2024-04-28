package andre.dev.campus.remote

import andre.dev.campus.remote.response.CampiResponse
import retrofit2.http.GET

interface CampusService {
    @GET("campus")
    suspend fun getCampi(): CampiResponse
}
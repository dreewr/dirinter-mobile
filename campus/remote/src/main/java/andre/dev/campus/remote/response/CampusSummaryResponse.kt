package andre.dev.campus.remote.response

import andre.dev.campus.model.CampusSummary
import com.google.gson.annotations.SerializedName

data class CampusSummaryResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

fun CampusSummaryResponse.toDomainModel() = CampusSummary(
    id = id, name = name
)

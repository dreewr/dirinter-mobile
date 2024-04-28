package andre.dev.campus.remote.response

import andre.dev.campus.domain.model.CampusSummary
import com.google.gson.annotations.SerializedName

data class CampiResponse(
    val campi: List<CampusSummaryResponse>
)

data class CampusResponse(
    @SerializedName("id") val id: String,
    //TODO: FINALIZAR MAPEAMENTO DOS DADOS DO CAMPUS
)

data class CampusSummaryResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

fun CampusSummaryResponse.toCampusSummary() = CampusSummary(
    id = id, name = name
)

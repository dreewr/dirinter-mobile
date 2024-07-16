package andre.dev.campus.remote.response

import andre.dev.campus.model.CampusSummary
import com.google.gson.annotations.SerializedName

data class CampiResponse(
    val campi: List<CampusSummaryResponse>
)





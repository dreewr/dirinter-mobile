package andre.dev.campus.domain

import andre.dev.campus.domain.model.CampusSummary

fun interface GetCampiUseCase {
    suspend fun getCampi(): List<CampusSummary>

}
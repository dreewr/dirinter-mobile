package andre.dev.campus.interactor

import andre.dev.campus.model.CampusSummary

fun interface GetCampiUseCase {
    suspend fun getCampi(): List<CampusSummary>

}
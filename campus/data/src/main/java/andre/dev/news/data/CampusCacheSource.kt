package andre.dev.news.data

import andre.dev.campus.domain.model.CampusSummary

interface CampusCacheSource {
    suspend fun insertAll(campi: List<CampusSummary>)

    suspend fun getCampi(): List<CampusSummary>

}
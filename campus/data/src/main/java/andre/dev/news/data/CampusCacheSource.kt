package andre.dev.news.data

import andre.dev.campus.model.Campus
import andre.dev.campus.model.CampusSummary

interface CampusCacheSource {
    suspend fun insertAll(campi: List<CampusSummary>)

    suspend fun getCampi(): List<CampusSummary>

    suspend fun insertCampus(campus: Campus)

    suspend fun getCampus(id: String): Campus? 

}
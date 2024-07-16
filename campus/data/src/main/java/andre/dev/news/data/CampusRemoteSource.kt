package andre.dev.news.data

import andre.dev.campus.model.Campus
import andre.dev.campus.model.CampusSummary

interface CampusRemoteSource {
    suspend fun getCampi(): List<CampusSummary>

    suspend fun getCampus(id: String): Campus
}
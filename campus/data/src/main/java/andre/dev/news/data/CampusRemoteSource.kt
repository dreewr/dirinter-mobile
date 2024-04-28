package andre.dev.news.data

import andre.dev.campus.domain.model.CampusSummary

interface CampusRemoteSource {
    suspend fun getCampi(): List<CampusSummary>
}
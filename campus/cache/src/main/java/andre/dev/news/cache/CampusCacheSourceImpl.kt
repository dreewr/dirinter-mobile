package andre.dev.news.cache

import andre.dev.campus.model.Campus
import andre.dev.campus.model.CampusSummary
import andre.dev.news.data.CampusCacheSource
import andre.dev.news.model.toDomainModel
import andre.dev.news.model.toEntity
import javax.inject.Inject

class CampusCacheSourceImpl @Inject constructor(
    private val database: CampusDatabase
) : CampusCacheSource {

    override suspend fun insertAll(campi: List<CampusSummary>) {
        database.newsDao().insertAll(campi.map { it.toEntity() })
    }

    override suspend fun getCampi(): List<CampusSummary> =
        database.newsDao().getSummaries().map { it.toDomainModel() }

    override suspend fun insertCampus(campus: Campus) {
        TODO("Not yet implemented")
    }

    override suspend fun getCampus(id: String): Campus? {
        TODO("Not yet implemented")
    }
}
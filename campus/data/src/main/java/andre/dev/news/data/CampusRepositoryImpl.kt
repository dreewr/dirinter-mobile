package andre.dev.news.data

import andre.dev.campus.CampusRepository
import andre.dev.campus.model.Campus
import andre.dev.campus.model.CampusSummary
import javax.inject.Inject

class CampusRepositoryImpl @Inject constructor(
    private val cacheSource: CampusCacheSource,
    private val remoteSource: CampusRemoteSource
) : CampusRepository {
    override suspend fun getCampi(): List<CampusSummary> = cacheSource.getCampi().ifEmpty {
        remoteSource.getCampi().also {
            cacheSource.insertAll(it)
        }
    }

    override suspend fun getCampus(id: String): Campus {
        return remoteSource.getCampus(id)
    }

//    override suspend fun getCampus(id: String): Campus = cacheSource.getCampus(id).also {
//
//    }
}
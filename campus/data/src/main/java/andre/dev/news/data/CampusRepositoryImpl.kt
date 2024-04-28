package andre.dev.news.data

import andre.dev.campus.domain.CampusRepository
import andre.dev.campus.domain.model.CampusSummary
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
}
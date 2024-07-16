package andre.dev.campus.remote

import andre.dev.campus.model.Campus
import andre.dev.campus.model.CampusSummary
import andre.dev.campus.remote.response.toDomainModel
import andre.dev.news.data.CampusRemoteSource
import javax.inject.Inject

class CampusRemoteSourceImpl @Inject constructor(
    private val service: CampusService
) : CampusRemoteSource {

    override suspend fun getCampi(): List<CampusSummary> = service.getCampi().campi.map {
        it.toDomainModel()
    }

    override suspend fun getCampus(id: String): Campus =
        service.getCampus(id).toDomainModel()

}
package andre.dev.campus.remote

import andre.dev.campus.domain.model.CampusSummary
import andre.dev.campus.remote.response.toCampusSummary
import andre.dev.news.data.CampusRemoteSource
import javax.inject.Inject

class CampusRemoteSourceImpl @Inject constructor(
    private val service: CampusService
) : CampusRemoteSource {

    override suspend fun getCampi(): List<CampusSummary> = service.getCampi().campi.map {
        it.toCampusSummary()
    }
}
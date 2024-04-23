package andre.dev.news.remote

import andre.dev.core.network.ServiceFactory

class CampusServiceFactory {
    fun getServiceFactory() = ServiceFactory.createService(CampusService::class.java)
}
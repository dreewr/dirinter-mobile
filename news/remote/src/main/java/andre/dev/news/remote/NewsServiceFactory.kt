package andre.dev.news.remote

import andre.dev.core.network.ServiceFactory

class NewsServiceFactory {
    fun getServiceFactory() = ServiceFactory.createService(NewsService::class.java)
}
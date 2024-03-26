package andre.dev.news.remote

import andre.dev.core.network.ServiceFactory

class NewsServiceFactory {
    fun getServiceFactory() = ServiceFactory.createService(
        NewsService::class.java,
        "http://10.0.2.2:8080/api/" //LOCALHOST
    )
}
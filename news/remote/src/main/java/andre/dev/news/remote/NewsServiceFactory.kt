package andre.dev.news.remote

import andre.dev.core.network.ServiceFactory

class NewsServiceFactory {
    fun getServiceFactory() = ServiceFactory.createService(
        NewsService::class.java,
        "http://derint-mobile-api-env-1.eba-6diqhtjr.us-east-1.elasticbeanstalk.com/api/"
    )
}
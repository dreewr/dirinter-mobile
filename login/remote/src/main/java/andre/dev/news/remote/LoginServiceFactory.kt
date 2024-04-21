package andre.dev.news.remote

import andre.dev.core.network.ServiceFactory

class LoginServiceFactory {
    fun getServiceFactory() = ServiceFactory.createService(LoginService::class.java)
}
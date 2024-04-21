package andre.dev.news.data

import andre.dev.news.domain.LoginRepository
import andre.dev.news.domain.model.User
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(
    private val cacheSource: LoginCacheSource,
    private val remoteSource: LoginRemoteSource
) : LoginRepository {
    override suspend fun executeLogin(id: String, password: String): User {

        //TODO: COLOCAR CACHE
        return remoteSource.executeLogin(id, password)
    }

}
package andre.dev.news.data

import andre.dev.news.domain.LoginRepository
import andre.dev.news.domain.model.User
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(
    private val cacheSource: LoginCacheSource,
    private val remoteSource: LoginRemoteSource
) : LoginRepository {

    override suspend fun executeLogin(id: String, password: String) =
        remoteSource.executeLogin(id, password).apply {
            cacheSource.saveUser(this)
        }

    override suspend fun getLoggedUser(): User? = cacheSource.getUser()

}
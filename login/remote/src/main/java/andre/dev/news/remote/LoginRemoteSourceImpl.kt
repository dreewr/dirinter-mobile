package andre.dev.news.remote

import andre.dev.news.data.LoginRemoteSource
import andre.dev.news.domain.model.User
import andre.dev.news.remote.model.LoginRequest
import andre.dev.news.remote.model.UserResponse
import javax.inject.Inject

class LoginRemoteSourceImpl @Inject constructor(
    private val service: LoginService
) : LoginRemoteSource {

    override suspend fun executeLogin(id: String, password: String): User {
        return service.executeLogin(LoginRequest(id, password)).run {
            User(
                id, name
            )
        }
    }
}
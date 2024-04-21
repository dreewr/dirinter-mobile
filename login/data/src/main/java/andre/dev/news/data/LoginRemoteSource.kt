package andre.dev.news.data

import andre.dev.news.domain.model.User

interface LoginRemoteSource {
    suspend fun executeLogin(id: String, password: String): User
}
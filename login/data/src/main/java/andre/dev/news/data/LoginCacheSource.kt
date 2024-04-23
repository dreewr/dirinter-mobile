package andre.dev.news.data

import andre.dev.news.domain.model.User

interface LoginCacheSource {
    suspend fun saveUser(user: User)
    suspend fun getUser(): User?
}
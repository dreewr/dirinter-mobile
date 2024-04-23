package andre.dev.news.cache

import andre.dev.news.data.LoginCacheSource
import andre.dev.news.domain.model.User
import andre.dev.news.model.toEntity
import andre.dev.news.model.toUser
import javax.inject.Inject

class LoginCacheSourceImpl @Inject constructor(
    private val database: LoginDatabase
) : LoginCacheSource {
    override suspend fun saveUser(user: User) = with(database.loginDao()) {
        deleteAllUsers()
        insertUser(user.toEntity())
    }

    override suspend fun getUser(): User? = database.loginDao().getUser()?.let { userEntity->
        if (userEntity.expirationTimestamp >= System.currentTimeMillis()) return userEntity.toUser()
        database.loginDao().deleteAllUsers()
        return null
    }

}
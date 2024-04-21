package andre.dev.news.domain

import andre.dev.news.domain.model.User

fun interface ExecuteLoginUseCase {
    suspend fun executeLogin(id: String, password: String): User

}

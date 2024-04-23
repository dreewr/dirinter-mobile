package andre.dev.news.domain.interactor

import andre.dev.news.domain.model.User

fun interface GetLoggedUserUseCase {
    suspend fun getLoggedUser(): User?
}

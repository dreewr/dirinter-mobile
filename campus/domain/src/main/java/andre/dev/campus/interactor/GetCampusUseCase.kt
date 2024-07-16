package andre.dev.campus.interactor

import andre.dev.campus.model.Campus

fun interface GetCampusUseCase {
    suspend fun getCampus(id: String): Campus
}
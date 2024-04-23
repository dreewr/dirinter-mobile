package andre.dev.campus.domain

import andre.dev.campus.domain.model.Campus

fun interface GetArticleByIdUseCase {
    suspend fun getArticle(id: String): Campus

}
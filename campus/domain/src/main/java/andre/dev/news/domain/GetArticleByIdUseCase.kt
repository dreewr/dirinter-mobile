package andre.dev.news.domain

import andre.dev.news.domain.model.Campus

fun interface GetArticleByIdUseCase {
    suspend fun getArticle(id: String): Campus

}
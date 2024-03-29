package andre.dev.news.domain

import andre.dev.news.domain.model.Article

fun interface GetArticleByIdUseCase {
    suspend fun getArticle(id: String): Article

}
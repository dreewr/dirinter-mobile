package andre.dev.news.domain

import andre.dev.news.domain.model.Article

fun interface GetArticlesUseCase {
    suspend fun getArticles(): List<Article>

}
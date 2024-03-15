package andre.dev.news.remote.response

import andre.dev.news.domain.model.Article


data class ArticlesResponse(
    val articles: List<Article>
)


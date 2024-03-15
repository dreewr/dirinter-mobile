package andre.dev.news.domain

import andre.dev.news.domain.model.Article

interface NewsRepository {
    suspend fun doSomething(): List<Article>
}
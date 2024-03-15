package andre.dev.news.data

import andre.dev.news.domain.model.Article

interface NewsRemoteSource{
    suspend fun doSomething(): List<Article>
}
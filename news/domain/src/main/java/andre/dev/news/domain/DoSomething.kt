package andre.dev.news.domain

import andre.dev.news.domain.model.Article

fun interface DoSomething{
    suspend fun invoke(): List<Article>

}
//Adicionar o role do invoke para nomear os parametros
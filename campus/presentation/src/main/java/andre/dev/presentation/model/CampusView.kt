package andre.dev.presentation.model

import andre.dev.news.domain.model.Article
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/*TODO: USE MAPPERS*/
data class ArticleView(
    val id: String,
    val author: String,
    val title: String,
    val content: String,
    val thumbnailUrl: String,
    val publishingDetails: String
) {
    constructor(article: Article) : this(
        id = article.id,
        author = article.author,
        title = article.title,
        content = article.content,
        thumbnailUrl = article.thumbnailUrl,
        publishingDetails =  "Publicado às ${article.publishingTimestamp.toFormattedDate()}," +
                " última modificação às ${article.lastEditTimestamp.toFormattedDate()}"
    )
}

//TODO: MOVER PARA UM UTILS
fun Long.toFormattedDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy 'às' HH:mm", Locale.getDefault())
    return dateFormat.format(Date(this))
}

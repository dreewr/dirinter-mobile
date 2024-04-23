package andre.dev.presentation.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/*TODO: USE MAPPERS*/
data class CampusView(
    val id: String
) {
//    constructor(article: Article) : this(
//        id = article.id,
//        author = article.author,
//        title = article.title,
//        content = article.content,
//        thumbnailUrl = article.thumbnailUrl,
//        publishingDetails =  "Publicado às ${article.publishingTimestamp.toFormattedDate()}," +
//                " última modificação às ${article.lastEditTimestamp.toFormattedDate()}"
//    )
}

//TODO: MOVER PARA UM UTILS
fun Long.toFormattedDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy 'às' HH:mm", Locale.getDefault())
    return dateFormat.format(Date(this))
}

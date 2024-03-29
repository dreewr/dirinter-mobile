package andre.dev.presentation.model

import andre.dev.news.domain.model.ArticleSummary
import java.text.SimpleDateFormat
import java.util.Locale

data class ArticleSummaryView(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val publishingDate: String,
)

internal fun mapToArticleView(articleSummary: ArticleSummary): ArticleSummaryView = with(articleSummary){
    val dateFormat = SimpleDateFormat("dd/MM 'às' HH:mm", Locale.getDefault())
    val formattedDate = dateFormat.format(publishingTimestamp)

    return ArticleSummaryView(
        id = id,
        title = title,
        thumbnailUrl = thumbnailUrl,
        publishingDate = formattedDate,
    )
}
package andre.dev.presentation.model

import andre.dev.news.domain.model.ArticleSummary

data class ArticleSummaryView(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val publishingDetails: String,
) {
    constructor(article: ArticleSummary) : this(
        id = article.id,
        title = article.title,
        thumbnailUrl = article.thumbnailUrl,
        publishingDetails = "Publicado ${article.publishingTimestamp.toFormattedDate()}"
    )
}
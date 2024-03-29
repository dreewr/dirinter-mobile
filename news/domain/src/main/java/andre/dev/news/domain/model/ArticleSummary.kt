package andre.dev.news.domain.model

data class ArticleSummary(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val publishingTimestamp: Long
)
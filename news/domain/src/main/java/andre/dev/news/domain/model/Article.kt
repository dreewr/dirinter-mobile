package andre.dev.news.domain.model

data class Article(
    val id: String,
    val title: String,
    val summary: String,
    val thumbnailUrl: String,
    val publishingTimestamp: Long
)
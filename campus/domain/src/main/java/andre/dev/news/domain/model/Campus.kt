package andre.dev.news.domain.model

data class Campus(
    val id: String,
    val author: String,
    val title: String,
    val content: String,
    val thumbnailUrl: String,
    val publishingTimestamp: Long,
    val lastEditTimestamp: Long
)


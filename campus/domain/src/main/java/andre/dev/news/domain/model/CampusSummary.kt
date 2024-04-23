package andre.dev.news.domain.model

data class CampusSummary(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val publishingTimestamp: Long
)
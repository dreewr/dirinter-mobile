package andre.dev.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CampusSummaryEntity(
    @PrimaryKey val id: String,
    val title: String,
    val thumbnailUrl: String,
    val timestamp: Long,
    val expirationTimestamp: Long
)

//fun ArticleSummaryEntity.toArticle(): ArticleSummary = ArticleSummary(
//    id = id,
//    title = title,
//    thumbnailUrl = thumbnailUrl,
//    publishingTimestamp = timestamp
//)
//
//fun ArticleSummary.toEntity(insertionTimestamp: Long): ArticleSummaryEntity = ArticleSummaryEntity(
//    id = id,
//    title = title,
//    thumbnailUrl = thumbnailUrl,
//    timestamp = publishingTimestamp,
//    expirationTimestamp = insertionTimestamp + (24 * 60 * 60 * 1000)
//)
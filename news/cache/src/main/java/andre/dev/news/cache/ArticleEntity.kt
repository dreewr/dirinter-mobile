package andre.dev.news.cache

import andre.dev.news.domain.model.Article
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    @PrimaryKey val id: String,
    val title: String,
    val summary: String,
    val thumbnailUrl: String,
    val timestamp: Long,
    val expirationTimestamp: Long
)

fun ArticleEntity.toArticle(): Article = Article(
    id = id,
    title = title,
    summary = summary,
    thumbnailUrl = thumbnailUrl,
    publishingTimestamp = timestamp
)

fun Article.toEntity(insertionTimestamp: Long): ArticleEntity = ArticleEntity(
    id = id,
    title = title,
    summary = summary,
    thumbnailUrl = thumbnailUrl,
    timestamp = publishingTimestamp,
    expirationTimestamp = insertionTimestamp + (24 * 60 * 60 * 1000)
)
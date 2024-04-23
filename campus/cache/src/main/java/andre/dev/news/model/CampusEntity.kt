package andre.dev.news.model

import andre.dev.news.domain.model.Article
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    @PrimaryKey val id: String,
    val author: String,
    val title: String,
    val content: String,
    val thumbnailUrl: String,
    val publishingTimestamp: Long,
    val lastEditTimestamp: Long,
    val expirationTimestamp: Long
)

fun ArticleEntity.toArticle(): Article = Article(
    id = id,
    author = author,
    title = title,
    content = content,
    thumbnailUrl = thumbnailUrl,
    publishingTimestamp = publishingTimestamp,
    lastEditTimestamp = lastEditTimestamp
)

fun Article.toEntity(expirationOffsetMillis: Long = 24 * 60 * 60 * 1000): ArticleEntity = ArticleEntity(
    id = id,
    author = author,
    title = title,
    content = content,
    thumbnailUrl = thumbnailUrl,
    publishingTimestamp = publishingTimestamp,
    lastEditTimestamp = lastEditTimestamp,
    expirationTimestamp = lastEditTimestamp + expirationOffsetMillis
)


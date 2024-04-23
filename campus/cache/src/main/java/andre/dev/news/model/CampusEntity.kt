package andre.dev.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CampusEntity(
    @PrimaryKey val id: String
)

//fun ArticleEntity.toArticle(): Article = Article(
//    id = id,
//    author = author,
//    title = title,
//    content = content,
//    thumbnailUrl = thumbnailUrl,
//    publishingTimestamp = publishingTimestamp,
//    lastEditTimestamp = lastEditTimestamp
//)
//
//fun Article.toEntity(expirationOffsetMillis: Long = 24 * 60 * 60 * 1000): ArticleEntity = ArticleEntity(
//    id = id,
//    author = author,
//    title = title,
//    content = content,
//    thumbnailUrl = thumbnailUrl,
//    publishingTimestamp = publishingTimestamp,
//    lastEditTimestamp = lastEditTimestamp,
//    expirationTimestamp = lastEditTimestamp + expirationOffsetMillis
//)


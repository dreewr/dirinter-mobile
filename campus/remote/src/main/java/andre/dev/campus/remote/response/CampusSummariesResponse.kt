package andre.dev.campus.remote.response

import com.google.gson.annotations.SerializedName

data class ArticleSummariesResponse(
    val articles: List<ArticleSummaryResponse>
)

data class CampusResponse(
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("publishingTimestamp") val publishingTimestamp: Long,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String,
    @SerializedName("content") val content: String,
    @SerializedName("lastEditTimestamp") val lastEditTimestamp: Long
)

data class ArticleSummaryResponse(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("publishingTimestamp") val publishingTimestamp: Long,
    @SerializedName("thumbnailUrl") var thumbnailUrl: String
)

//fun ArticleResponse.toArticle() = Article(
//        id = id,
//        author = author,
//        title = title,
//        content = content,
//        thumbnailUrl = thumbnailUrl,
//        publishingTimestamp = publishingTimestamp,
//        lastEditTimestamp = lastEditTimestamp
//    )
//
//fun ArticleSummaryResponse.toArticle() = ArticleSummary(
//    id = id,
//    title = title,
//    thumbnailUrl = thumbnailUrl,
//    publishingTimestamp = publishingTimestamp
//)

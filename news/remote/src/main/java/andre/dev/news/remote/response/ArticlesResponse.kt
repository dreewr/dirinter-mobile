package andre.dev.news.remote.response

import andre.dev.news.domain.model.Article
import com.google.gson.annotations.SerializedName


data class ArticlesResponse(
    val articles: List<ArticleResponse>
)

data class ArticleResponse(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String,
    @SerializedName("publishingTimestamp") val publishingTimestamp: Long
)

fun ArticleResponse.toArticle() = Article(
    id = id,
    title = title,
    summary = summary,
    thumbnailUrl = thumbnailUrl,
    publishingTimestamp = publishingTimestamp
)

package andre.dev.news.domain.model

data class Article(
    val id: String,
    val title: String,
    val summary: String,
    val thumbnailUrl: String,
    val timestamp: Int
)


//TODO: REMOVER DEPOIS
fun getSomeFakeNews(startTimeStamp: Int = 100, numberOfItems: Int = 10): List<Article> {
    return List(numberOfItems) { index ->
        val timestamp = startTimeStamp - 1 - index
        Article(
            id = timestamp.toString(),
            title = "News Title with ID $timestamp",
            summary = "This is a summary of the news with ID $timestamp.",
            thumbnailUrl = "https://example.com/thumbnail/$timestamp.jpg",
            timestamp = timestamp
        )
    }
}
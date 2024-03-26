package andre.dev.news.domain

sealed class NewsException: Exception() {
    class NetworkError : NewsException()
    class UnknownError: NewsException()
}
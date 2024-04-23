package andre.dev.news.domain

sealed class CampusException: Exception() {
    class NetworkError : CampusException()
    class UnknownError: CampusException()
}
package andre.dev.campus.domain

sealed class CampusException: Exception() {
    class NetworkError : CampusException()
    class UnknownError: CampusException()
}
package andre.dev.campus

sealed class CampusException: Exception() {
    class NetworkError : CampusException()
    class UnknownError: CampusException()
}
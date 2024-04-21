package andre.dev.news.domain


sealed class LoginException: Exception() {
    class UnknownError: LoginException()

    class NetworkError: LoginException()
    class EmptyCredentialsException: LoginException()
}

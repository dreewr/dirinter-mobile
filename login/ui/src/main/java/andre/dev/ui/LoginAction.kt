package andre.dev.ui

sealed class LoginAction {
    data class OnLoginSuccessful(val id: String) : LoginAction()
}

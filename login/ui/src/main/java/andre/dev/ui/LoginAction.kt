package andre.dev.ui

sealed class LoginAction {
    data class LoginSuccessful(val id: String) : LoginAction()
}

package andre.dev.ui

sealed class NewsAction {
    data class NewsSelected(val id: String) : NewsAction()
}

package andre.dev.ui

sealed class NewsAction {
    data class OnNewsSelected(val id: String) : NewsAction()
}

package andre.dev.ui

sealed class CampusAction {
    data class OnCampusSelected(val id: String) : CampusAction()
}

package andre.dev.ui

sealed class CampusAction {
    data class CampusSelected(val id: String, val campusName: String) : CampusAction()
}

package andre.dev.presentation.model

import andre.dev.campus.model.Campus
import andre.dev.campus.model.Contact
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/*TODO: USE MAPPERS*/
data class CampusUiModel(
    val overview: CampusOverview,   // Content for the "About" tab
    val contacts: List<Contact>,    // Content for the "Contacts" tab
    val courses: List<String>       // Content for the "Courses" tab
)

//TODO: RENAME
data class CampusOverview(
    val name: String,
    val description: String,
    val branches: List<BranchOverview>
)

//TODO: RENAME
data class BranchOverview(
    val name: String,
    val address: String,
    val imageUrl: String,
    val courses: List<String>
)

//TODO: MOVER PARA UM UTILS
fun Long.toFormattedDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy 'às' HH:mm", Locale.getDefault())
    return dateFormat.format(Date(this))
}

//TODO: MOVER PARA UM UTILS
fun Campus.toUiModel(): CampusUiModel {
    val overview = CampusOverview(
        name = name,
        description = description,
        branches = branches.map { branch ->
            BranchOverview(
                name = branch.name,
                address = "${branch.address}, ${branch.city}",
                imageUrl = branch.imageUrl,
                courses = branch.courses
            )
        }
    )

    val contacts = branches.flatMap { it.contacts }
    val courses = branches.flatMap { it.courses }.distinct()

    return CampusUiModel(overview, contacts, courses)
}
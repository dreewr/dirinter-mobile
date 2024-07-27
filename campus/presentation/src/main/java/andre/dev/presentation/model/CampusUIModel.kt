package andre.dev.presentation.model

import andre.dev.campus.model.Campus
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//https://medium.com/@AndroidRayz/unlocking-the-power-of-mappers-in-android-simplifying-data-transformation-in-clean-architecture-d4fb7722827e
/*TODO: USE MAPPERS*/
data class CampusUIModel(
    val about: AboutUIModel,          // Content for the "About" tab
    val contacts: List<ContactUIModel>,           // Content for the "Contacts" tab
    val courses: List<CourseSectionUIModel>       // Content for the "Courses" tab
)

data class ContactUIModel(
    val name: String
)

data class CourseSectionUIModel(
    val category: String,
    val courses: List<CourseUiModel>
)

data class CourseUiModel(
    val name: String,
    val tags: List<String>
)

data class AboutUIModel(
    val name: String,
    val description: String,
    val branches: List<BranchUIModel>
)

//Representação local
data class BranchUIModel(
    val name: String,
    val address: String,
    val imageUrl: String,
    val courses: List<String>
)

data class BranchMapUiModel(
    val mapUrl: String,

    )

//TODO: MOVER PARA UM MAPPER
fun Campus.toUiModel(): CampusUIModel {
    val overview = AboutUIModel(
        name = name,
        description = description,
        branches = branches.map { branch ->
            BranchUIModel(
                name = branch.name,
                address = "${branch.address}, ${branch.city}",
                imageUrl = branch.imageUrl,
                courses = listOf()//branch.courses
            )
        }
    )

    val contacts = branches.flatMap { it.contacts }

    return CampusUIModel(overview, listOf(),
        branches.flatMap { branch ->
            branch.courseSections.map { section ->
                CourseSectionUIModel(
                    section.category,
                    section.courses.map { course ->
                        CourseUiModel(course.name, course.tags)
                    }
                )
            }
        })
}

fun Long.toFormattedDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy 'às' HH:mm", Locale.getDefault())
    return dateFormat.format(Date(this))
}

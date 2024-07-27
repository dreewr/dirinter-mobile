package andre.dev.campus.remote.response

import andre.dev.campus.model.Branch
import andre.dev.campus.model.Building
import andre.dev.campus.model.Campus
import andre.dev.campus.model.Contact
import andre.dev.campus.model.Course
import andre.dev.campus.model.CourseSection
import andre.dev.campus.model.Marker
import com.google.gson.annotations.SerializedName
import kotlin.text.Typography.section

data class CampusResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("branches") val branches: List<BranchResponse>
)

data class BranchResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("campusId") val campusId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("postalCode") val postalCode: String,
    @SerializedName("city") val city: String,
    @SerializedName("country") val country: String,
    @SerializedName("courseSections") val courseSections: List<CourseSectionResponse>,
    @SerializedName("contacts") val contacts: List<ContactResponse>,
    @SerializedName("imageUrl") val branchImageUrl: String,
    @SerializedName("buildings") val buildings: List<BuildingResponse>,
    @SerializedName("markers") val markers: List<MarkerResponse>,
    @SerializedName("branchMapUrl") var branchMapUrl: String
)

data class BuildingResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("pointsOfInterest") val pointsOfInterest: List<PointOfInterestResponse>
)

data class MarkerResponse(
    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("color") val color: String
)

data class PointOfInterestResponse(
    @SerializedName("description") val description: String
)

data class ContactResponse(
    @SerializedName("name") val name: String,
    @SerializedName("department") val department: String,
    @SerializedName("email") val email: String,
    @SerializedName("phoneNumber") val phoneNumber: String
)


fun CampusResponse.toDomainModel(): Campus {  ///MOVE TO MAPPER CLASS
    return Campus(
        id = id,
        name = name,
        description = description,
        branches = branches.map { branch ->
            Branch(
                id = branch.id,
                name = branch.name,
                address = branch.address,
                postalCode = branch.postalCode,
                city = branch.city,
                country = branch.country,
                courseSections = branch.courseSections.map { section ->
                    CourseSection(
                        section.category,
                        section.courses.map { course ->
                            Course(course.name, course.tags)
                        }
                    )
                },
                contacts = branch.contacts.map { contact ->
                    Contact(
                        name = contact.name,
                        department = contact.department,
                        email = contact.email,
                        phoneNumber = contact.phoneNumber
                    )
                },
                imageUrl = branch.branchImageUrl,
                buildings = branch.buildings.map { building ->
                    Building(
                        building.name,
                        building.pointsOfInterest.map { it.description }
                    )
                },
                markers = branch.markers.map { marker ->
                    Marker(
                        type = Marker.MarkerType.valueOf(marker.type),
                        name = marker.name,
                        color = Marker.MarkerColor.valueOf(marker.color)
                    )
                }
            )
        })
}

enum class CourseCategoryResponse {
    @SerializedName("GRADUACAO_BACHARELADO")
    GRADUACAO_BACHARELADO,

    @SerializedName("GRADUACAO_TECNOLOGIA")
    GRADUACAO_TECNOLOGIA,

    @SerializedName("GRADUACAO_LICENCIATURA")
    GRADUACAO_LICENCIATURA,

    @SerializedName("ESPECIALIZACAO")
    ESPECIALIZACAO,

    @SerializedName("MESTRADO")
    MESTRADO,

    @SerializedName("DOUTORADO")
    DOUTORADO,

    @SerializedName("LINGUAS_ESTRANGEIRAS")
    LINGUAS_ESTRANGEIRAS
}
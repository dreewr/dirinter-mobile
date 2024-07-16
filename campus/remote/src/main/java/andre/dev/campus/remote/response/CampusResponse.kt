package andre.dev.campus.remote.response

import andre.dev.campus.model.Branch
import andre.dev.campus.model.Campus
import andre.dev.campus.model.Contact
import andre.dev.campus.model.PointOfInterest
import andre.dev.campus.model.Sector
import com.google.gson.annotations.SerializedName

data class CampusResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("branches") val branches: List<BranchResponse>
)

data class BranchResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("postalCode") val postalCode: String,
    @SerializedName("city") val city: String,
    @SerializedName("country") val country: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("courses") val courses: List<String>,
    @SerializedName("contacts") val contacts: List<ContactResponse>,
    @SerializedName("sectors") val sectors: List<SectorResponse>,
    @SerializedName("imageUrl") val branchImageUrl: String
)

data class ContactResponse(
    @SerializedName("name") val name: String,
    @SerializedName("department") val department: String,
    @SerializedName("email") val email: String,
    @SerializedName("phoneNumber") val phoneNumber: String
)

data class SectorResponse(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("pointsOfInterest") val pointsOfInterest: List<PointOfInterestResponse>
)

data class PointOfInterestResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("description") val description: String
)

fun CampusResponse.toDomainModel(): Campus {
    return Campus(
        id = id,
        name = name,
        description = description,
        branches = branches.map { branchResponse ->
            Branch(
                id = branchResponse.id,
                name = branchResponse.name,
                address = branchResponse.address,
                postalCode = branchResponse.postalCode,
                city = branchResponse.city,
                country = branchResponse.country,
                email = branchResponse.email,
                phone = branchResponse.phone,
                courses = branchResponse.courses,
                contacts = branchResponse.contacts.map { contactResponse ->
                    Contact(
                        name = contactResponse.name,
                        department = contactResponse.department,
                        email = contactResponse.email,
                        phoneNumber = contactResponse.phoneNumber
                    )
                },
                sectors = branchResponse.sectors.map { sectorResponse ->
                    Sector(
                        name = sectorResponse.name,
                        description = sectorResponse.description,
                        pointsOfInterest = sectorResponse.pointsOfInterest.map { poiResponse ->
                            PointOfInterest(
                                id = poiResponse.id,
                                name = poiResponse.name,
                                type = poiResponse.type,
                                description = poiResponse.description
                            )
                        }
                    )
                },
                imageUrl = branchResponse.branchImageUrl
            )
        }
    )
}
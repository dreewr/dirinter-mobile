package andre.dev.campus.model

data class Campus(
    val id: Int,
    val name: String,
    val description: String,
    val branches: List<Branch>
)

data class Branch(
    val id: Int,
    val name: String,
    val address: String,
    val postalCode: String,
    val city: String,
    val country: String,
    val courseSections: List<CourseSection>,
    val contacts: List<Contact>,
    val imageUrl: String,
    val buildings: List<Building>,
    val markers: List<Marker>
)

data class Marker(
    val type: MarkerType,
    val name: String,
    val color: MarkerColor
) {
    enum class MarkerType {
        ACCESS_PEDESTRIAN,
        ACCESS_VEHICLE,
        PASSAGEWAY
    }

    enum class MarkerColor {
        BLUE,
        RED,
        GREEN
    }
}

data class Building(
    val name: String,
    val pointsOfInterest: List<String>
)

data class Contact(
    val name: String,
    val department: String,
    val email: String,
    val phoneNumber: String
)
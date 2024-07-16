package andre.dev.campus.model

// Domain Model Classes
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
    val email: String,
    val phone: String,
    val courses: List<String>,
    val contacts: List<Contact>,
    val sectors: List<Sector>,
    val imageUrl: String
)

data class Contact(
    val name: String,
    val department: String,
    val email: String,
    val phoneNumber: String
)

data class Sector(
    val name: String,
    val description: String,
    val pointsOfInterest: List<PointOfInterest>
)

data class PointOfInterest(
    val id: Int,
    val name: String,
    val type: String,
    val description: String
)



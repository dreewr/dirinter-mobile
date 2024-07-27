package andre.dev.campus.model

data class CourseSection(
    val category: String,  // Using String to match the JSON structure
    val courses: List<Course>
)
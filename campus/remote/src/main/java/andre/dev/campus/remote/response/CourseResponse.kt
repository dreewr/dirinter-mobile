package andre.dev.campus.remote.response

import andre.dev.campus.model.Course
import com.google.gson.annotations.SerializedName

data class CourseSectionResponse(
    @SerializedName("category") val category: String,  // Using String to match the JSON structure
    @SerializedName("courses") val courses: List<Course>
)

data class CourseResponse(
    @SerializedName("name") val name: String,
    @SerializedName("tags") val tags: List<String>
)
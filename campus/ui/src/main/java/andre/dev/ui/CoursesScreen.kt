package andre.dev.ui

import andre.dev.presentation.model.CourseSectionUIModel
import andre.dev.presentation.model.CourseUiModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CoursesScreen(courses: List<CourseSectionUIModel>) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp)) {
        courses.forEachIndexed { index, (category, courses) ->
            item {
                CourseSection(category, courses, index < courses.size - 1)
            }
        }
    }
}


@Composable
fun CourseSection(title: String, courses: List<CourseUiModel>, showDivider: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(PaddingValues(vertical = 16.dp))
        )
        //Spacer(modifier = Modifier.height(16.dp))

        courses.forEach { course ->
            CourseItem(course)
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (showDivider) Divider()

    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CourseItem(course: CourseUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(text = course.name, style = MaterialTheme.typography.headlineSmall)
        if (course.tags.isNotEmpty()) {
            FlowRow(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                course.tags.forEach { tag ->
                    Text(
                        text = tag,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

package andre.dev.ui

import andre.dev.campus.model.Branch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun CoursesTabContent(branches: List<Branch>) {
//    val pagerState = rememberPagerState()
//    Column {
//        HorizontalPager(count = branches.size, state = pagerState) { page ->
//            BranchTab(branch = branches[page])
//        }
//    }
//}
//
//@Composable
//fun BranchTab(branch: Branch) {
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text(text = branch.name, style = MaterialTheme.typography.h6)
//        branch.courses.groupBy { it.category }.forEach { (category, courses) ->
//            CourseCategoryItem(category, courses)
//        }
//    }
//}
//
//@Composable
//fun CourseCategoryItem(category: CourseCategory, courses: List<Course>) {
//    var expanded by remember { mutableStateOf(false) }
//
//    Column {
//        Text(
//            text = category.name,
//            style = MaterialTheme.typography.subtitle1,
//            modifier = Modifier
//                .clickable { expanded = !expanded }
//                .padding(vertical = 8.dp)
//        )
//        if (expanded) {
//            courses.forEach { course ->
//                Text(text = course.name, style = MaterialTheme.typography.body1, modifier = Modifier.padding(start = 16.dp))
//            }
//        }
//    }
//}
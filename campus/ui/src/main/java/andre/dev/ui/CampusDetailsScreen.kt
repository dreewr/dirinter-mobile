package andre.dev.ui

import andre.dev.lib.State
import andre.dev.presentation.CampusDetailsViewModel
import andre.dev.presentation.model.CampusUIModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CampusDetailsScreen(campusId: String, viewModelProvider: ViewModelProvider.Factory) {
    val viewModel: CampusDetailsViewModel = viewModel(factory = viewModelProvider)
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(campusId) {
        viewModel.getCampus(campusId)
    }

    TabsComponent(state = state) { viewModel.getCampus(campusId) }
}

@Composable
fun TabsComponent(state: State<CampusUIModel>, retryAction: () -> Unit) {
    val tabs = listOf("Sobre", "Cursos", "Mapa", "Contatos")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                )
            }
        }

        TabContent(state = state, selectedTabIndex = selectedTabIndex, retryAction)
    }
}

@Composable
fun TabContent(
    state: State<CampusUIModel>,
    selectedTabIndex: Int,
    retryAction: () -> Unit,
) {
    when (state) {
        is State.Loading -> Loading(modifier = Modifier.fillMaxSize())
        is State.Success -> when (selectedTabIndex) {
            0 -> CampusOverview(overview = state.data.about)
            1 -> CoursesScreen(courses = state.data.courses)
            2 -> TabContentTwo(data = "asdasdada")
        }
        is State.Failure -> RetryMessage {
            retryAction.invoke()
        }
    }
}

@Composable
fun TabContentTwo(data: String) {
    // Display data specific for Tab 2
    Text(text = "Content for Tab 2: ${data}")
}



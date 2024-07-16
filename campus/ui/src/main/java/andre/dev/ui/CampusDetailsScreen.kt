package andre.dev.ui

import andre.dev.lib.State
import andre.dev.presentation.CampusDetailsViewModel
import andre.dev.presentation.model.BranchOverview
import andre.dev.presentation.model.CampusOverview
import andre.dev.presentation.model.CampusUiModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
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
fun TabsComponent(state: State<CampusUiModel>, retryAction: () -> Unit) {
    val tabs = listOf("Sobre", "Contato", "Cursos", "Setores")
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
fun TabContent(state: State<CampusUiModel>, selectedTabIndex: Int, retryAction: () -> Unit) {
    when (state) {
        is State.Loading -> Loading(modifier = Modifier.fillMaxSize())
        is State.Success -> when (selectedTabIndex) {
            0 -> CampusOverview(overview = state.data.overview)
            1 -> TabContentTwo(data = "asdasdada")
        }

        is State.Failure -> RetryMessage {
            retryAction.invoke()
        }
    }
}

@Composable
fun CampusOverview(overview: CampusOverview) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Text(text = overview.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = overview.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(overview.branches) { branch ->
            BranchCard(branch)
        }
    }
}

@Composable
fun BranchCard(branch: BranchOverview) {
    //Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
    Column(/*modifier = Modifier.padding(16.dp)*/) {
        Text(text = branch.name, style = MaterialTheme.typography.headlineSmall)
        RemoteImage(imageUrl = branch.imageUrl)
        Text(text = branch.address, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        //Text("Cursos ofertados:", style = MaterialTheme.typography.labelLarge)
        //Spacer(modifier = Modifier.height(8.dp))

//        FlowRow(
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            branch.courses.forEach { course ->
//                Text(text = course, style = MaterialTheme.typography.bodyMedium)
//            }
//        }
    }
    // }
}


@Composable
fun TabContentTwo(data: String) {
    // Display data specific for Tab 2
    Text(text = "Content for Tab 2: ${data}")
}



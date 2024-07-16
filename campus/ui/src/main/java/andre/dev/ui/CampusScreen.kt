package andre.dev.ui

import andre.dev.campus.model.CampusSummary
import andre.dev.lib.State
import andre.dev.presentation.CampusViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CampusScreen(
    onAction: (CampusAction) -> Unit,
    viewModelProvider: ViewModelProvider.Factory
) {
    val viewModel: CampusViewModel = viewModel(factory = viewModelProvider)
    val state by viewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    LazyColumn(modifier = Modifier.fillMaxSize(), state = lazyListState) {
        item {
            when (val currentState = state) {
                is State.Loading -> Loading(Modifier.fillParentMaxSize())

                is State.Failure -> RetryMessage {
                    viewModel.fetchCampi()
                }
                is State.Success -> {
                    SuccessContent(campuses = currentState.data, onAction = onAction)
                }
            }
        }
    }
}

@Composable
fun SuccessContent(campuses: List<CampusSummary>, onAction: (CampusAction) -> Unit) {
    campuses.forEach { campus ->
        CampusItem(campus = campus, onAction = onAction)
        Divider()
    }
}

@Composable
fun CampusItem(campus: CampusSummary, onAction: (CampusAction) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAction(CampusAction.CampusSelected(campus.id, campus.name)) }
            .padding(16.dp)
    ) {
        Text(
            text = campus.name,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Go to details",
            modifier = Modifier.size(24.dp)
        )
    }
}

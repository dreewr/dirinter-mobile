package andre.dev.ui

import andre.dev.presentation.CampusViewModel
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CampusScreen(
    onAction: (CampusAction) -> Unit, viewModelProvider: ViewModelProvider.Factory
) {

    val viewModel: CampusViewModel = viewModel(factory = viewModelProvider)
    val pagingState by viewModel.uiState.collectAsState()

    val lazyListState = rememberLazyListState()

}
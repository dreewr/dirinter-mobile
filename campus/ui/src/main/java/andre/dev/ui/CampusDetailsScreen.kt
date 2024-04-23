package andre.dev.ui

import andre.dev.lib.State
import andre.dev.presentation.CampusDetailsViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun NewsDetailsScreen(newsId: String, viewModelProvider: ViewModelProvider.Factory) {
    val viewModel: CampusDetailsViewModel = viewModel(factory = viewModelProvider)
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(newsId) {
        viewModel.fetchArticle(newsId)
    }

    when (state) {
        is State.Loading -> Box(

            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        is State.Success -> {

        }

        is State.Failure -> RetryMessage(message = "Tente novamente.") {
            viewModel.fetchArticle(newsId)
        }

    }
}

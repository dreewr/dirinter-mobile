package andre.dev.ui

import andre.dev.lib.State
import andre.dev.news.domain.model.Article
import andre.dev.presentation.NewsViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun NewsScreen(viewModelProvider: ViewModelProvider.Factory) {
    val viewModel: NewsViewModel = viewModel(factory = viewModelProvider)
    val pagingState by viewModel.uiState.collectAsState()

    val lazyListState = rememberLazyListState()
    val context = LocalContext.current

    LazyColumn(modifier = Modifier.fillMaxSize(), state = lazyListState) {

        items(pagingState.loadedNews) { article ->
            ArticleItem(article = article)
        }

        item {
            when (pagingState.currentState) {
                is State.Loading -> Box(
                    modifier = if (pagingState.loadedNews.isEmpty()) Modifier.fillParentMaxSize()
                    else Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

                is State.Failure -> Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text =/*pagingState.currentState.message ?:*/ "An error occurred")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.fetchArticles() }) {
                            Text("Retry")
                        }
                    }
                }

                is State.Success -> InfiniteListHandler(
                    lazyListState = lazyListState,
                    onLoadMore = {
                        Toast.makeText(context, "Loading more articles...", Toast.LENGTH_SHORT)
                            .show()
                        viewModel.fetchArticles()
                    }
                )

            }
        }
    }
}

@Composable
fun ArticleItem(
    article: Article
) {
    Card(
        modifier = Modifier
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = article.thumbnailUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth() // Ensure the image matches the Card's width
                    .clip(RoundedCornerShape(8.dp)) // Rounded corners for the image
                    .aspectRatio(4f / 2f), // 3:2 aspect ratio
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp)) // Vertical spacing between the image and text
            Text(
                text = article.title,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp)) // Vertical spacing between the title and summary
            Text(
                text = article.summary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun InfiniteListHandler(
    lazyListState: LazyListState,
    buffer: Int = 0,
    onLoadMore: () -> Unit
) {
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = lazyListState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            lastVisibleItemIndex > (totalItems - buffer)
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore.value }
            .distinctUntilChanged()
            .collect {
                onLoadMore()
            }
    }
}
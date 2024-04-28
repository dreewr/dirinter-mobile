package andre.dev.ui

import andre.dev.lib.State
import andre.dev.presentation.NewsViewModel
import andre.dev.presentation.model.ArticleSummaryView
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun NewsScreen(
    onAction: (NewsAction) -> Unit, viewModelProvider: ViewModelProvider.Factory
) {

    val viewModel: NewsViewModel = viewModel(factory = viewModelProvider)
    val pagingState by viewModel.uiState.collectAsState()

    val lazyListState = rememberLazyListState()

    LazyColumn(modifier = Modifier.fillMaxSize(), state = lazyListState) {
        itemsIndexed(pagingState.loadedNews) { index, article ->
            ArticleItem(article = article,
                showDivider = index < pagingState.loadedNews.size - 1,
                onItemClick = {
                    onAction(NewsAction.NewsSelected(article.id))
                })
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
                    modifier = if (pagingState.loadedNews.isEmpty()) Modifier.fillParentMaxSize()
                    else Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    RetryMessage("An error occurred") {
                        viewModel.fetchArticles()
                    }
                }

                is State.Success -> {
                    InfiniteListHandler(
                        lazyListState = lazyListState, onLoadMore = viewModel::fetchArticles
                    )
                }
            }
        }
    }
}

@Composable
fun ArticleItem(
    article: ArticleSummaryView, onItemClick: (String) -> Unit,
    showDivider: Boolean
) {
    Column(modifier = Modifier
        .clickable { onItemClick(article.id) }
        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.thumbnailUrl)
                .crossfade(true)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .aspectRatio(4f / 2f),
            contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.title,
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.publishingDetails, maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall
        )
        if (showDivider) Divider(modifier = Modifier.padding(top = 16.dp))
    }

}

@Composable
fun InfiniteListHandler(
    lazyListState: LazyListState, buffer: Int = 0, onLoadMore: () -> Unit
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
        snapshotFlow { loadMore.value }.distinctUntilChanged().collect {
            onLoadMore()
        }
    }

}
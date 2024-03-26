package andre.dev.presentation

import andre.dev.lib.FailureType
import andre.dev.lib.State
import andre.dev.news.domain.GetArticlesUseCase
import andre.dev.news.domain.model.Article
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getArticles: GetArticlesUseCase, private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow(PaginationState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchArticles()
    }

    fun fetchArticles() = viewModelScope.launch(dispatcher) {
        if (!_uiState.value.hasMorePages) return@launch

        flow {
            emit(getArticles.getArticles(
                _uiState.value.loadedNews.minOfOrNull { it.publishingTimestamp }
                    ?: System.currentTimeMillis(), PAGE_SIZE))
        }.onStart {
            _uiState.value = _uiState.value.copy(currentState = State.Loading())
        }.catch { exception ->
            _uiState.value = _uiState.value.copy(
                currentState = State.Failure(
                    FailureType.GenericFailure, message = exception.localizedMessage
                )
            )
        }.collect { articles ->
            _uiState.value = PaginationState(
                currentState = State.Success(articles),
                loadedNews = _uiState.value.loadedNews + articles,
                hasMorePages = articles.size == PAGE_SIZE
            )
        }
    }

    data class PaginationState(
        val currentState: State<List<Article>> = State.Loading(),
        val loadedNews: List<Article> = listOf(),
        val hasMorePages: Boolean = true
    )

    companion object {
        private const val PAGE_SIZE = 3
    }
}






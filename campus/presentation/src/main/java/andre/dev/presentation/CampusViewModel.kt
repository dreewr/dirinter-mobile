package andre.dev.presentation

import andre.dev.lib.State
import andre.dev.news.domain.GetArticlesUseCase
import andre.dev.news.domain.model.ArticleSummary
import andre.dev.presentation.model.CampusSummaryView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class CampusViewModel @Inject constructor(
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
            emit(getArticles.getArticles(_uiState.value.thresholdTimestamp, PAGE_SIZE))
        }.onStart {
            _uiState.value = _uiState.value.copy(currentState = State.Loading())
        }.catch {
            _uiState.value = _uiState.value.copy(
                currentState = State.Failure()
            )
        }.collect { articles ->
            _uiState.value = PaginationState(
                currentState = State.Success(articles),
                loadedNews = _uiState.value.loadedNews + articles.map(::CampusSummaryView),
                hasMorePages = articles.size == PAGE_SIZE,
                thresholdTimestamp = minOf(
                    _uiState.value.thresholdTimestamp,
                    articles.minOfOrNull { it.publishingTimestamp } ?: Long.MAX_VALUE
                )
            )
        }
    }

    data class PaginationState(
        val currentState: State<List<ArticleSummary>> = State.Loading(),
        val loadedNews: List<CampusSummaryView> = listOf(),
        val hasMorePages: Boolean = true,
        val thresholdTimestamp: Long = Long.MAX_VALUE
    )

    companion object {
        private const val PAGE_SIZE = 3
    }
}






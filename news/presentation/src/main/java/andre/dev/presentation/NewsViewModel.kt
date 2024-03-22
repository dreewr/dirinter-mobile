package andre.dev.presentation

import andre.dev.lib.FailureType
import andre.dev.lib.State
import andre.dev.lib.isLoading
import andre.dev.news.domain.GetArticlesUseCase
import andre.dev.news.domain.model.Article
import andre.dev.news.domain.model.getSomeFakeNews
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
    private val getArticles: GetArticlesUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow(PaginationState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchArticles()
    }

    fun fetchArticles() = viewModelScope.launch(dispatcher) {

        flow {
            emit(getArticles.getArticles())
        }.onStart {
            _uiState.value = PaginationState(State.Loading(), _uiState.value.loadedNews)
        }.catch {
            _uiState.value = PaginationState(
                State.Failure(FailureType.GenericFailure, message = it.localizedMessage),
                _uiState.value.loadedNews
            )
        }.collect {
            delay(1000)
//            _uiState.value = PaginationState(
//                State.Failure(FailureType.GenericFailure, message = it.localizedMessage),
//                _uiState.value.loadedNews
//            )
            _uiState.value = PaginationState(
                State.Success(it),
                _uiState.value.loadedNews + it
            )
        }
    }

    data class PaginationState(
        val currentState: State<List<Article>> = State.Loading(),
        val loadedNews: List<Article> = listOf()
    )
}






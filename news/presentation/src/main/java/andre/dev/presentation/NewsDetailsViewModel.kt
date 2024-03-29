package andre.dev.presentation

import andre.dev.lib.FailureType
import andre.dev.lib.State
import andre.dev.news.domain.GetArticleByIdUseCase
import andre.dev.news.domain.model.Article
import andre.dev.news.domain.model.ArticleSummary
import andre.dev.presentation.model.ArticleView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsDetailsViewModel @Inject constructor(
    private val getArticleById: GetArticleByIdUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow<State<ArticleView>>(State.Loading())

    val uiState = _uiState.asStateFlow()

    fun fetchArticle(newsId: String) {
        viewModelScope.launch(dispatcher) {
            flow {
                emit(getArticleById.getArticle(newsId))
            }.onStart {
                // Emit loading state before starting the flow
                _uiState.value = State.Loading()
            }.catch {
                _uiState.value = State.Failure(FailureType.GenericFailure)
            }.collect { article ->
                _uiState.value = State.Success(ArticleView(article))
            }
        }
    }
}
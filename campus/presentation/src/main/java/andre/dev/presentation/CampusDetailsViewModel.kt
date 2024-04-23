package andre.dev.presentation

import andre.dev.lib.State
import andre.dev.campus.domain.GetArticleByIdUseCase
import andre.dev.presentation.model.CampusView
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

class CampusDetailsViewModel @Inject constructor(
//    private val getArticleById: GetArticleByIdUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow<State<CampusView>>(State.Loading())

    val uiState = _uiState.asStateFlow()

    fun fetchArticle(newsId: String) {
//        viewModelScope.launch(dispatcher) {
//            flow {
//                emit(getArticleById.getArticle(newsId))
//            }.onStart {
//                _uiState.value = State.Loading()
//            }.catch {
//                _uiState.value = State.Failure()
//            }.collect { article ->
//                _uiState.value = State.Failure()
//            }
//        }
    }
}
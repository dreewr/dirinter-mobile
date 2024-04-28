package andre.dev.presentation

import andre.dev.campus.domain.GetCampiUseCase
import andre.dev.lib.State
import andre.dev.presentation.model.CampusView
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CampusDetailsViewModel @Inject constructor(
    //private val getArticleById: GetCampiUseCase,
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
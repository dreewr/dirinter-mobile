package andre.dev.presentation

import andre.dev.lib.State
import andre.dev.campus.domain.GetArticlesUseCase
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CampusViewModel @Inject constructor(
    /*private val getArticles: GetArticlesUseCase,*/ private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow(State.Loading<String>())
    val uiState = _uiState.asStateFlow()

}






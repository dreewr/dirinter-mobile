package andre.dev.presentation

import andre.dev.campus.interactor.GetCampusUseCase
import andre.dev.campus.model.Campus
import andre.dev.lib.State
import andre.dev.presentation.model.CampusUiModel
import andre.dev.presentation.model.toUiModel
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

class CampusDetailsViewModel @Inject constructor(
    private val getCampusUseCase: GetCampusUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow<State<CampusUiModel>>(State.Loading())
    val uiState = _uiState.asStateFlow()

    fun getCampus(campusId: String) {
        viewModelScope.launch(dispatcher) {
            flow {
                emit(getCampusUseCase.getCampus(campusId))
            }.onStart {
                _uiState.value = State.Loading()
            }.catch {
                _uiState.value = State.Failure()
            }.collect { campus ->
                delay(1000)
                _uiState.value = State.Success(campus.toUiModel())
            }
        }
    }
}
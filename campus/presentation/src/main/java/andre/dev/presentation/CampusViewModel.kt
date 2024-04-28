package andre.dev.presentation

import andre.dev.campus.domain.GetCampiUseCase
import andre.dev.campus.domain.model.CampusSummary
import andre.dev.lib.State
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

class CampusViewModel @Inject constructor(
    private val getCampiUseCase: GetCampiUseCase, private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow<State<List<CampusSummary>>>(State.Loading())
    val uiState = _uiState.asStateFlow()

    init {
        fetchCampi()
    }
    fun fetchCampi() {
        viewModelScope.launch(dispatcher) {
            flow {
                emit(getCampiUseCase.getCampi())
            }.onStart {
                _uiState.value = State.Loading()
            }.catch {
                _uiState.value = State.Failure()
            }.collect { campi ->
                _uiState.value = State.Success(campi)
            }
        }
    }

}






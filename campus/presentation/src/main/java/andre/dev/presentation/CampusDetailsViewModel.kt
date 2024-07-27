package andre.dev.presentation

import andre.dev.campus.interactor.GetCampusUseCase
import andre.dev.campus.model.Campus
import andre.dev.campus.model.Course
import andre.dev.lib.State
import andre.dev.news.presentation.R
import andre.dev.presentation.model.AboutUIModel
import andre.dev.presentation.model.BranchUIModel
import andre.dev.presentation.model.CampusUIModel
import andre.dev.presentation.model.ContactUIModel
import andre.dev.presentation.model.CourseSectionUIModel
import andre.dev.presentation.model.CourseUiModel
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
    private val _uiState = MutableStateFlow<State<CampusUIModel>>(State.Loading())
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
                _uiState.value = State.Success(campus.toUiModel())
            }
        }
    }
}


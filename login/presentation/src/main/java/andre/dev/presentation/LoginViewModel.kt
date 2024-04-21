package andre.dev.presentation

import andre.dev.lib.State
import andre.dev.news.domain.ExecuteLoginUseCase
import andre.dev.news.domain.LoginException.EmptyCredentialsException
import andre.dev.news.domain.model.User
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val executeLoginUseCase: ExecuteLoginUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow<State<User>?>(null)
    val uiState = _uiState.asStateFlow().filterNotNull()

    fun executeLogin(id: String, password: String) = viewModelScope.launch(dispatcher) {

        if (setOf(id, password).any { it.isBlank() }) {
            _uiState.value = State.Failure(EmptyCredentialsException())
            return@launch
        }

        flow {
            emit(executeLoginUseCase.executeLogin("", ""))
        }.onStart {
            _uiState.value = State.Loading()
        }.catch {
            _uiState.value = State.Failure()
        }.collect { user ->
            _uiState.value = State.Success(user)
        }
    }
}






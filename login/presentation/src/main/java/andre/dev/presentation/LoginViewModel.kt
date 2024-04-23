package andre.dev.presentation

import andre.dev.lib.State
import andre.dev.news.domain.interactor.ExecuteLoginUseCase
import andre.dev.news.domain.LoginException.EmptyCredentialsException
import andre.dev.news.domain.interactor.GetLoggedUserUseCase
import andre.dev.news.domain.model.User
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val executeLoginUseCase: ExecuteLoginUseCase,
    private val getLoggedUserUseCase: GetLoggedUserUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _loginState = MutableStateFlow<State<User>?>(null)
    val loginState = _loginState.asStateFlow().filterNotNull()

    init {
        getLoggedUser()
    }

    private fun getLoggedUser() = viewModelScope.launch(dispatcher) {
        flow {
            emit(getLoggedUserUseCase.getLoggedUser())
        }.collect { user ->
            user?.let {
                _loginState.value = State.Success(user)
            }
        }
    }

    fun executeLogin(id: String, password: String) = viewModelScope.launch(dispatcher) {

        if (setOf(id, password).any { it.isBlank() }) {
            _loginState.value = State.Failure(EmptyCredentialsException())
            return@launch
        }

        flow {
            emit(executeLoginUseCase.executeLogin(id, password))
        }.onStart {
            _loginState.value = State.Loading()
        }.catch {
            _loginState.value = State.Failure()
        }.collect { user ->
            _loginState.value = State.Success(user)
        }
    }
}






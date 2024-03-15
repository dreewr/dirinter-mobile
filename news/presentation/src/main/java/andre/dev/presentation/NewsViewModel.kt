package andre.dev.presentation

import andre.dev.news.domain.DoSomething
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val useCase: DoSomething,
    private val dispatcher: CoroutineDispatcher
): ViewModel(){

    fun doSomething() = viewModelScope.launch(dispatcher) {

        flow {
            emit(useCase.invoke())
        }.catch {
            it
        }.collect {
            it
        }

    }
}
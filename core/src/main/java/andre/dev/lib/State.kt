package andre.dev.lib

import kotlin.Exception

sealed interface State<T>{
    open class Failure<T>(exception: Exception = Exception()): State<T>
    data class Success<T>(val data: T): State<T>
    class Loading<T> : State<T>
}

fun State<*>.isLoading() = this is State.Loading<*>

fun State<*>.isSuccess() = this is State.Success<*>

fun State<*>.isFailure() = this is State.Failure<*>
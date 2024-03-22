package andre.dev.lib

sealed interface State<T>{
    open class Failure<T>(val errorType: FailureType, val message: String? = null): State<T>
    data class Success<T>(val data: T): State<T>
    class Loading<T> : State<T>
}

sealed interface FailureType{
    object NetworkFailure : FailureType
    object GenericFailure : FailureType
}

fun State<*>.isLoading() = this is State.Loading<*>

fun State<*>.isSuccess() = this is State.Success<*>

fun State<*>.isFailure() = this is State.Failure<*>
package com.murta.githubrepositories.domain.utils

sealed class State<T>(
    open val data: T? = null,
    open val error: ErrorInformation? = null,
) {
    data class Loading<T>(
        override val data: T? = null,
        override val error: ErrorInformation? = null,
    ) : State<T>()

    data class Success<T>(override val data: T?) : State<T>(data = data)

    data class Error<T>(
        override val data: T? = null,
        override val error: ErrorInformation,
    ) : State<T>(null, error)

    val isLoading get() = this is Loading
    val isNotLoading get() = isLoading.not()
    val isSuccess get() = this is Success
    val isNotSuccess get() = isSuccess.not()
    val isError get() = this is Error
    val isNotError get() = isError.not()
}

data class ErrorInformation(
    val message: String? = null,
    val cause: Throwable? = null,
)

fun Exception.toErrorInformation(message: String? = null) = ErrorInformation(
    message = message
        ?: localizedMessage
        ?: "Ocorreu um erro por aqui, tente novamente mais tarde.",
    cause = cause
)

interface StateBearer {
    val state: State<Any>
}
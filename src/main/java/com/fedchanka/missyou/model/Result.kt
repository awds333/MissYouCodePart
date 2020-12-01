package com.fedchanka.missyou.model

sealed class Result<out R : Any, E : Any> {
    val isSuccess: Boolean get() = this !is Error

    val isError: Boolean get() = this is Error

    inline fun ifSuccess(action: (value: R) -> Unit): Result<R, E> {
        if (this is Success) {
            action(data)
        }

        return this
    }

    inline fun ifError(action: (error: E) -> Unit): Result<R, E> {
        if (this is Error) {
            action(error)
        }

        return this
    }

    /*inline fun <NewR : Any> flatMap(mapper: (R) -> Result<NewR, E>): Result<NewR, E> {
        return when (this) {
            is Success      -> mapper(data)
            is Error        -> Error(error)
            is EmptySuccess -> throw RuntimeException("flatMapping empty success!")
        }
    }

    inline fun <NewR : Any, NewE : Any> map(changeSuccess: (R) -> NewR,
                                            changeError: (E) -> NewE): Result<NewR, NewE> {
        return when (this) {
            is Success      -> Success(changeSuccess(data))
            is Error        -> Error(changeError(error))
            is EmptySuccess -> throw RuntimeException("mapping empty success!")
        }
    }

    inline fun <NewR : Any> mapSuccess(mapper: (R) -> NewR): Result<NewR, E> = map(mapper, { it })

    inline fun <NewE : Any> mapError(mapper: (E) -> NewE): Result<R, NewE> = map({ it }, mapper)*/

    fun successOrNull(): R? {
        return when (this) {
            is Success      -> data
            is Error        -> null
            is EmptySuccess -> throw RuntimeException("successOrNull on EmptySuccess!")
        }
    }
}

object EmptySuccess : Result<Nothing, Throwable>()

data class Success<R : Any, E : Any>(val data: R) : Result<R, E>()

data class Error<R : Any, E : Any>(val error: E) : Result<R, E>()

fun <R : Any, E : Any> R.asSuccess(): Success<R, E> = Success(this)

fun <R : Any, E : Any> E.asError(): Error<R, E> = Error(this)
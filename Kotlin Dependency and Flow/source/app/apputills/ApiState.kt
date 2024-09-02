package com.learn.app.kotlins.apputills

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

sealed class ApiState<out T> {
    data class Success<out R>(val data: R) : ApiState<R>()
    data class Failure(val message: String) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> {
                "Success"
            }

            is Failure -> {
                "Failure"
            }

            is Loading -> {
                "Loading"
            }
        }
    }
}

fun <T, R> ApiState<T>.map(transfrom: (T) -> R): ApiState<R> {
    return when (this) {
        is ApiState.Success -> {
            ApiState.Success(transfrom(data))
        }

        is ApiState.Failure -> {
            ApiState.Failure(message)
        }

        is ApiState.Loading -> {
            ApiState.Loading
        }
    }
}

fun <T> Flow<ApiState<T>>.doOnSuccess(action: suspend (T) -> Unit): Flow<ApiState<T>> {
    return transform { result ->
        if (result is ApiState.Success) {
            action(result.data)
        }

        emit(result)
    }

}

fun <T> Flow<ApiState<T>>.doOnFailure(action: suspend (String) -> Unit): Flow<ApiState<T>> {
    return transform { result ->
        if (result is ApiState.Failure) {
            action(result.message)
        }
        emit(result)
    }
}

fun <T> Flow<ApiState<T>>.doOnLoading(action: suspend () -> Unit): Flow<ApiState<T>> {
   return transform { result ->
        if (result is ApiState.Loading) {
            action()
        }
         emit(result)
    }

}

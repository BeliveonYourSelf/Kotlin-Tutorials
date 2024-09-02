package com.learn.app.kotlins.apputills

data class ApiStatus<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = "",
    val isEmpty: Boolean? = false
) {
    enum class Status {
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun <T> Loading(): ApiStatus<T> {
            return ApiStatus(Status.LOADING)
        }
        fun <T> SuccessApiCall(data : T?) : ApiStatus<T>{
            return  ApiStatus(Status.SUCCESS,data)
        }
        fun <T> Error(errorMessage : String?) : ApiStatus<T>{
            return  ApiStatus(Status.ERROR, message = errorMessage)
        }
    }
}

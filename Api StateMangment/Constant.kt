package com.festum.nearbybusiness.user.ui.DependencyInjecation.Utills

import android.util.Log
import com.festum.nearbybusiness.user.ui.DependencyInjecation.ApiClient
import com.festum.nearbybusiness.user.ui.DependencyInjecation.ResponseModel.UserLogin
import com.festum.nearbybusiness.user.ui.DependencyInjecation.ResponseUtils.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


fun <T> result(call: suspend () -> Response<T>): Flow<ApiResponse<T?>> = flow {
    emit(ApiResponse.Loading)

    try {
        val response = call()
        response.let {
            if (response.isSuccessful) {
                Log.e("TAG", "result: -----------> Result Class", )
                emit(ApiResponse.Success(response.body()))
            } else {
                response.errorBody()?.let {error ->
                    emit(ApiResponse.Failure(error.toString()))
                    error.close()
                    Log.e("TAG", "unSuccessfull: ----------->   $it", )

                }

            }
        }

    } catch (e: Exception) {
        emit(ApiResponse.Failure(e.message.toString()))
        Log.e("TAG", "Exception: ----------->  ${e.message}", )
        e.printStackTrace()
    }
}

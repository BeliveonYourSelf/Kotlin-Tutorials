package com.learn.app.kotlins.di.repository

import android.util.Log
import com.google.gson.GsonBuilder
import com.learn.app.kotlins.apputills.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<ApiState<T>> {
        return flow {
            emit(ApiState.Loading)
            val response = apiCall()
            Log.e("safeApiCall", "getMoviesLits 200: ----> "+ GsonBuilder().setPrettyPrinting().create().toJson(response.body()) )
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    emit(ApiState.Success(data))
                }else{
                    val error = response.errorBody().toString()
                    emit(ApiState.Failure(error))
                }
            } else {
                emit(ApiState.Failure(response.errorBody().toString()))
            }
        }.catch {
            emit(ApiState.Failure(it.message.toString()))
        }.flowOn(Dispatchers.IO)
    }
}
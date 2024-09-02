package com.learn.app.kotlins.di.repository

import android.util.Log
import com.google.gson.GsonBuilder
import com.learn.app.kotlins.apiclient.ApiServices
import com.learn.app.kotlins.apputills.ApiStatus
import com.learn.app.kotlins.datamodels.CoinResponse
import com.learn.app.kotlins.datamodels.ResponseCoinsMarkets
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun getCoinList(vs_currency: String): Flow<ApiStatus<CoinResponse>> {
        return flow {
            emit(ApiStatus.Loading())
            val results = apiServices.getCoinList(vs_currency)
            when (results.code()) {
                200 -> {
                    Log.e("getCoinList", "getCoinList 200: ----> "+GsonBuilder().setPrettyPrinting().create().toJson(results.body()) )
                    emit(ApiStatus.SuccessApiCall(results.body()))
                }
                400 -> {
                    emit(ApiStatus.Error(results.errorBody().toString()))
                }

                500 -> {
                    emit(ApiStatus.Error(results.errorBody().toString()))
                }
            }
        }.catch {
            emit(ApiStatus.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)
    }


}
package com.ai.girl.friend.chatting.networking

import com.ai.girl.friend.chatting.appconstants.AppConstant
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var httpLogginInterceptro = HttpLoggingInterceptor()


    private val requestInterceptor = Interceptor { chain ->
        val request = chain
            .request().newBuilder()
            .addHeader("Content-Type", "application/json")
//            .addHeader(
//                "Authorization",
//                "Bearer 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")
            .build()
        return@Interceptor chain.proceed(request)
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .connectTimeout(500, TimeUnit.SECONDS)
        .readTimeout(500, TimeUnit.SECONDS)
        .writeTimeout(500, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()


    private val instance: Retrofit = Retrofit.Builder()
        .baseUrl(AppConstant.baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()

    val apiService: ApiService by lazy {
        instance.create(ApiService::class.java)
    }

}



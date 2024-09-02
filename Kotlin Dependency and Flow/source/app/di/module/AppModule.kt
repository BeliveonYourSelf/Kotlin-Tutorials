package com.learn.app.kotlins.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.learn.app.kotlins.apiclient.ApiServices
import com.learn.app.kotlins.apputills.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun connectionTimeOut() = Constants.NETWORK_TIMEOUT



    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()


    @Provides
    fun provideOkHttpClient(apiKey : String): OkHttpClient {
        val httpLogginInterceptro = HttpLoggingInterceptor()
        httpLogginInterceptro.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        httpLogginInterceptro.setLevel(HttpLoggingInterceptor.Level.BODY)


        val requestInterceptor = Interceptor { chain ->
            val url = chain.request().url.newBuilder().addQueryParameter("x_cg_demo_api_key", Constants.API_KEY)
                    .build()
            val request = chain
                .request()
                .newBuilder()
                .addHeader("x-apihub-key",Constants.MOVIE_API_KEY)
                .addHeader("x-apihub-host","Movies-Verse.allthingsdev.co")
                .addHeader("x-apihub-endpoint","d3ee0b1f-e51c-46bc-99eb-c660726b0a1b")
                .build()
            return@Interceptor chain.proceed(request)
        }
        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(httpLogginInterceptro)
            .connectTimeout(2000,TimeUnit.SECONDS)
            .readTimeout(2000,TimeUnit.SECONDS)
            .writeTimeout(2000,TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): ApiServices {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices::class.java)

    }


}
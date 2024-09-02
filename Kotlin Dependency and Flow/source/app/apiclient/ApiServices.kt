package com.learn.app.kotlins.apiclient

import com.learn.app.kotlins.datamodels.CoinResponse
import com.learn.app.kotlins.datamodels.MovieDetailsResponse
import com.learn.app.kotlins.datamodels.Movies
import com.learn.app.kotlins.datamodels.MoviesListResponse
import com.learn.app.kotlins.datamodels.ResponseCoinsMarkets
import com.learn.app.kotlins.datamodels.ResponseDetailsCoin
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/popular")
    fun getPopularMovieList(@Query("page") page: Int): Call<MoviesListResponse>

    @GET("movie/{movie_id}")
    fun getMovieListDetails(@Path("movie_id") movieid: Int): Call<MovieDetailsResponse>

    @GET("movies/top-250-movies")
    suspend fun get250TopMoviesList() : Response<Movies>

    @GET("coins/markets")
   suspend fun getCoinList(@Query("vs_currency") vs_currency : String) : Response<CoinResponse>

   @GET("coins/{id}?sparkline=true")
   suspend fun getCoinDetails(@Path("id") id :String) : Response<ResponseDetailsCoin>
}
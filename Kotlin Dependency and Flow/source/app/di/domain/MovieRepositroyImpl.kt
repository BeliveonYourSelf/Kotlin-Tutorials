package com.learn.app.kotlins.di.domain

import com.learn.app.kotlins.apiclient.ApiServices
import com.learn.app.kotlins.apputills.ApiState
import com.learn.app.kotlins.datamodels.Movies
import com.learn.app.kotlins.di.repository.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiServices: ApiServices) :
    MovieRepository, BaseRepository() {
    override suspend fun get250TopMovies(): Flow<ApiState<Movies>> {
        return safeApiCall {
            apiServices.get250TopMoviesList()
        }
    }
}
package com.learn.app.kotlins.di.domain

import com.learn.app.kotlins.apputills.ApiState
import com.learn.app.kotlins.datamodels.Movies
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun get250TopMovies(): Flow<ApiState<Movies>>
}
package com.learn.app.kotlins.di.usecases

import android.util.Log
import com.learn.app.kotlins.apputills.ApiState
import com.learn.app.kotlins.apputills.map
import com.learn.app.kotlins.datamodels.Movy
import com.learn.app.kotlins.di.domain.MovieMapper
import com.learn.app.kotlins.di.domain.MovieRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val movieRepository: MovieRepositoryImpl,
    private val mapper: MovieMapper
) {
    suspend fun getMovies(): Flow<ApiState<List<Movy>?>> {
        return movieRepository.get250TopMovies().map { result ->
            result.map { movies ->
                mapper.fromMap(movies)
            }
        }
    }
}
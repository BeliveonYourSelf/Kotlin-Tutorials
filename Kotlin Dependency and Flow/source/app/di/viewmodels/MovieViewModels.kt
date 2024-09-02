package com.learn.app.kotlins.di.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.app.kotlins.apputills.doOnFailure
import com.learn.app.kotlins.apputills.doOnLoading
import com.learn.app.kotlins.apputills.doOnSuccess
import com.learn.app.kotlins.datamodels.Movy
import com.learn.app.kotlins.di.usecases.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModels @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {

    private val _movieList: MutableStateFlow<MovieStates> = MutableStateFlow(MovieStates())
    val movieList: StateFlow<MovieStates> = _movieList.asStateFlow()
    init {
        getMoviesList()
    }

    private fun getMoviesList() {
        viewModelScope.launch {
            useCase.getMovies()
                .doOnSuccess {
                    Log.e("TAG", "MovieViewModels---> doOnSuccess: ${it?.size}", )
                    _movieList.value = MovieStates(data = it!!)
                }
                .doOnFailure {
                    Log.e("TAG", "MovieViewModels---> doOnFailure: ", )
                    _movieList.value = MovieStates(error = it)
                }
                .doOnLoading {
                    Log.e("TAG", "MovieViewModels---> doOnLoading: ", )
                    _movieList.value = MovieStates(isLoading = true)
                }.collect()
        }
    }

}

data class MovieStates(
    val data: List<Movy> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)
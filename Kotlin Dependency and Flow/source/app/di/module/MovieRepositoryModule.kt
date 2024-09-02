package com.learn.app.kotlins.di.module

import com.learn.app.kotlins.di.domain.MovieRepository
import com.learn.app.kotlins.di.domain.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieRepositoryModule {

    @Binds
    abstract fun provideMovieRepository(repo: MovieRepositoryImpl): MovieRepository


}
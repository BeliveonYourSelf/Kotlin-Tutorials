package com.learn.app.kotlins.di.domain

import com.learn.app.kotlins.apputills.Mapper
import com.learn.app.kotlins.datamodels.Movies
import com.learn.app.kotlins.datamodels.Movy
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<Movies?, List<Movy>?> {
    override fun fromMap(from: Movies?): List<Movy>? {
        return from?.movies?.map {
            Movy(
                image = it.image,
                imdbRating = it.imdbRating,
                link = it.link,
                timeline = it.timeline,
                title = it.title,
                year = it.year
            )
        }
    }
}
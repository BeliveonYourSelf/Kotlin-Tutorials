package com.learn.app.kotlins.datamodels

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("movies")
    val movies: List<Movy>
)
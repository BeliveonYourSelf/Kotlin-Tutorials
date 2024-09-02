package com.learn.app.kotlins.apputills

interface Mapper<F, T> {
    fun fromMap(from: F): T
}
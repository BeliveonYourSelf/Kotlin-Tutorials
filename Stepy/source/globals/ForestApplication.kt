package com.steps.tracker.machine.analyzer.globals

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.android.material.color.DynamicColors
import com.steps.tracker.machine.analyzer.apputility.Constant
import com.steps.tracker.machine.analyzer.databases.AppDatabase

class ForestApplication : Application() {

    companion object {
        lateinit var appContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

    object AppContext {
        val context: Context
            get() = appContext
    }

}

val database =
    Room.databaseBuilder(
        ForestApplication.AppContext.context,
        AppDatabase::class.java,
        Constant.STEP_DB
    ).allowMainThreadQueries().build()
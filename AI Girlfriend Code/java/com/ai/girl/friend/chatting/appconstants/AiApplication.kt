package com.ai.girl.friend.chatting.appconstants

import android.app.Application
import android.content.Context

class AiApplication : Application() {
    companion object {
        lateinit var appContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    object AppContext {
        val context: Context
            get() {
                return appContext
            }
    }
}
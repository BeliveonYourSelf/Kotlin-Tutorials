package com.steps.tracker.machine.analyzer.services

import DateUtils.getStartOfDayMinus
import android.Manifest
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.room.Room
import com.steps.tracker.machine.analyzer.R
import com.steps.tracker.machine.analyzer.apputility.Constant
import com.steps.tracker.machine.analyzer.apputility.NotificationMangers
import com.steps.tracker.machine.analyzer.databases.AppDatabase
import com.steps.tracker.machine.analyzer.databases.models.Steps
import com.steps.tracker.machine.analyzer.features.ui.splash.SplashActivity

class ResetStepForegroundService : Service() {
    private lateinit var notification: NotificationCompat.Builder
    var pushData: PushData? = null
    private val binder = LocalBinder()
    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onCreate() {
        update()
        super.onCreate()
    }

    private fun update() {
        val appIntent = Intent(
            this, SplashActivity::class.java
        )
        val pendingIntent = PendingIntent.getActivity(
            this, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )

        notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NotificationCompat.Builder(this, Constant.CHANNEL_ID_UPDATE)
        } else {
            NotificationCompat.Builder(this)
        }
            .setSmallIcon(R.drawable.ic_step_exercise)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_step_exercise))
            .setContentTitle(this.getString(R.string.app_name))
            .setContentText("Update data")
            .setAutoCancel(true)
            .setOngoing(false)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .setTimeoutAfter(2000)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startForeground(
                NotificationMangers.FULLSCREEN_UPDATE_DATA_NOTIFICATION_ID,
                notification.build()
            )
        }
        var database: AppDatabase = Room.databaseBuilder(
            this,
            AppDatabase::class.java, Constant.STEP_DB
        )
            .allowMainThreadQueries()
            .build()

        var listRecord: List<Steps> = database.stepDao().getRecordStepsToPush(
            getStartOfDayMinus(System.currentTimeMillis(), 1),
            System.currentTimeMillis()
        )
    }

    inner class LocalBinder : Binder() {
        fun getService(): ResetStepForegroundService = this@ResetStepForegroundService
    }
}

interface PushData {
    fun pushComplete()
}
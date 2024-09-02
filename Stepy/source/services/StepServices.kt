package com.steps.tracker.machine.analyzer.services

import DateUtils.convertSecondToTime
import DateUtils.getStartOfHour
import android.Manifest
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.steps.tracker.machine.analyzer.R
import com.steps.tracker.machine.analyzer.apputility.Constant
import com.steps.tracker.machine.analyzer.apputility.NotificationMangers
import com.steps.tracker.machine.analyzer.apputility.NotificationMangers.Companion.FULLSCREEN_REMINDER_NOTIFICATION_ID
import com.steps.tracker.machine.analyzer.databases.AppDatabase
import com.steps.tracker.machine.analyzer.databases.models.Steps
import com.steps.tracker.machine.analyzer.features.ui.splash.SplashActivity
import com.steps.tracker.machine.analyzer.fragments.HomeFragment
import com.steps.tracker.machine.analyzer.prefrences.SharedPreferenceUtils
import java.util.Date

class StepServices : Service(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var accelator: Sensor? = null
    private var numSteps = 0
    lateinit var database: AppDatabase
    private lateinit var notification: NotificationCompat.Builder
    var currentStep = SharedPreferenceUtils.dayStep.toInt()
    var targetStep = SharedPreferenceUtils.targetStep.toInt()
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        SendNotification()
        return  START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelator = sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        sensorManager!!.registerListener(this,accelator,SensorManager.SENSOR_DELAY_FASTEST)
        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java, Constant.STEP_DB
        )
            .allowMainThreadQueries()
            .build()

        SendNotification()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onSensorChanged(event: SensorEvent?) {
        numSteps++
        stepCount()
    }

    private fun stepCount() {
        SharedPreferenceUtils.dayStep++
        if (SharedPreferenceUtils.dayStep > SharedPreferenceUtils.targetStep)
            SharedPreferenceUtils.targetStep = SharedPreferenceUtils.dayStep

        currentStep = SharedPreferenceUtils.dayStep.toInt()
        targetStep = SharedPreferenceUtils.targetStep.toInt()
        val currentTime = Date()
        var steps = database.stepDao()
            .getStepsHour(getStartOfHour(System.currentTimeMillis()), System.currentTimeMillis())
        if (steps != null) {
            database.stepDao().insert(
                Steps(
                    null,
                    1,
                    currentTime,
                    1,
                    Constant.CALO_STEP,
                    (SharedPreferenceUtils.stepLength * Constant.CM_TO_KM)
                )
            )
        } else {
            steps.step++
            steps.calo = (steps.step * Constant.CALO_STEP)
            steps.distance += SharedPreferenceUtils.stepLength * Constant.CM_TO_KM
            steps.activeTime = steps.activeTime.plus(1)
            steps.isPush = false
            database.stepDao().updateStep(steps)

        }
        HomeFragment.currentStep.postValue(SharedPreferenceUtils.dayStep.toInt())
        SendNotification()
    }

    private fun SendNotification() {
        val remoteViews = RemoteViews(this.packageName, R.layout.layout_notify_all).apply {
            setTextViewText(R.id.tv_number_step, SharedPreferenceUtils.dayStep.toString())
            setTextViewText(
                R.id.tv_number_calo,
                (SharedPreferenceUtils.dayStep * Constant.KcalOne).toInt().toString()
            )
            setTextViewText(R.id.tv_timer, (convertSecondToTime(SharedPreferenceUtils.dayStep)))
            setProgressBar(R.id.determinateBar, targetStep, currentStep, false)
            if (currentStep < targetStep / 4) {
                setProgressBar(R.id.determinateBar, targetStep, currentStep, false)
                setViewVisibility(R.id.determinateBar, View.VISIBLE)

                setViewVisibility(R.id.determinateBar2, View.GONE)
                setViewVisibility(R.id.determinateBar3, View.GONE)
                setViewVisibility(R.id.determinateBar4, View.GONE)
            } else if (currentStep < targetStep / 2) {
                setProgressBar(R.id.determinateBar2, targetStep, currentStep, false)
                setViewVisibility(R.id.determinateBar2, View.VISIBLE)

                setViewVisibility(R.id.determinateBar, View.GONE)
                setViewVisibility(R.id.determinateBar3, View.GONE)
                setViewVisibility(R.id.determinateBar4, View.GONE)
            } else if (currentStep <= (targetStep / 4) * 3) {
                setProgressBar(R.id.determinateBar3, targetStep, currentStep, false)
                setViewVisibility(R.id.determinateBar3, View.VISIBLE)

                setViewVisibility(R.id.determinateBar2, View.GONE)
                setViewVisibility(R.id.determinateBar, View.GONE)
                setViewVisibility(R.id.determinateBar4, View.GONE)
            } else {
                setProgressBar(R.id.determinateBar4, targetStep, currentStep, false)
                setViewVisibility(R.id.determinateBar4, View.VISIBLE)

                setViewVisibility(R.id.determinateBar2, View.GONE)
                setViewVisibility(R.id.determinateBar3, View.GONE)
                setViewVisibility(R.id.determinateBar, View.GONE)
            }
        }
        val appIntent = Intent(this, SplashActivity::class.java)
        val appPendingIntent = PendingIntent.getActivity(
            this,
            0,
            appIntent,
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(this, Constant.CHANNEL_ID_STEP)
        } else {
            NotificationCompat.Builder(this)
        }
            .setSmallIcon(R.drawable.ic_step_exercise)
            .setShowWhen(false)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_step_exercise))
            .setContentTitle(ContextCompat.getString(this, R.string.app_name))
            .setContentText(currentStep.toString())
            .setAutoCancel(false)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("${currentStep} ${ContextCompat.getString(this, R.string.app_name)}")
            )
            .setContentIntent(appPendingIntent)
            .setContent(remoteViews)
            .setCustomContentView(remoteViews)
            .setCustomBigContentView(remoteViews)
            .setDefaults(Notification.DEFAULT_ALL)
            .setPriority(Notification.PRIORITY_HIGH)  .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            startForeground(FULLSCREEN_REMINDER_NOTIFICATION_ID, notification.build())
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
        NotificationMangers.cancelNotification(
            this,
            FULLSCREEN_REMINDER_NOTIFICATION_ID,
            notification.build()
        )
        sensorManager!!.unregisterListener(this)
    }
}
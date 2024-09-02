package com.steps.tracker.machine.analyzer.features.ui.splash

import DateUtils.getEndOfDay
import DateUtils.getEndOfDayMinus
import DateUtils.getStartOfDay
import DateUtils.getStartOfDayMinus
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.steps.tracker.machine.analyzer.apputility.Constant
import com.steps.tracker.machine.analyzer.base.BaseBindingActivity
import com.steps.tracker.machine.analyzer.databinding.ActivitySplashBinding
import com.steps.tracker.machine.analyzer.features.ui.language.LanguageActivity
import com.steps.tracker.machine.analyzer.features.ui.language.LanguageUtil
import com.steps.tracker.machine.analyzer.features.ui.setupaccount.SelectHowTallActivity
import com.steps.tracker.machine.analyzer.prefrences.SharedPreferenceUtils
import java.util.Locale


class SplashActivity : BaseBindingActivity<ActivitySplashBinding>() {
    lateinit var activity: Activity
    private lateinit var local: Locale
    companion object{
        var IS_PUSH = false
    }
    override fun setBinding(layoutInflater: LayoutInflater): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@SplashActivity
    }

    override fun initView() {
        super.initView()
        activity = this

        local = Locale.getDefault()
        LanguageUtil.setupLanguage(this)
        creatNotificationChannels()
        getStepDays()
        openNextScreen()
    }

    override fun onClick(v: View?) {

    }

    private fun openNextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            launchActivity(getActivityIntent<SelectHowTallActivity> { })
//            if (SharedPreferenceUtils.firstOpenApp) {
//                Toast.makeText(getActivityContext(), "Lang", Toast.LENGTH_SHORT).show()
//                launchActivity(getActivityIntent<LanguageActivity>(true) { })
//            }else{
//                Toast.makeText(getActivityContext(), "else", Toast.LENGTH_SHORT).show()
//                launchActivity(getActivityIntent<SelectHowTallActivity> { })
//            }
//            if (ContextCompat.checkSelfPermission(activity,Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED)
//            {
//                startActivity(Intent(activity, MainActivity::class.java))
//
//            }else{
//                startActivity(Intent(activity, PermissionActivity::class.java))
//            }
        }, 2000)
    }

    private fun getStepDays() {
        SharedPreferenceUtils.dayStep = database.stepDao().getStepsDay(
            getStartOfDay(System.currentTimeMillis()),
            getEndOfDay(System.currentTimeMillis())
        )
        SharedPreferenceUtils.yesterdayStep = database.stepDao().getStepsDay(
            getStartOfDayMinus(System.currentTimeMillis(), 1),
            getEndOfDayMinus(System.currentTimeMillis(), 1)
        ) - SharedPreferenceUtils.dayStep

    }


    private fun creatNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val stepChannel = NotificationChannel(
                Constant.CHANNEL_ID_STEP,
                "CHANNEL_ID_STEP",
                NotificationManager.IMPORTANCE_HIGH
            )
            stepChannel.description = "CHANNEL_ID_STEP"
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(stepChannel)

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val alarmChannel = NotificationChannel(
                Constant.CHANNEL_ID_ALARM,
                "CHANNEL_ID_ALARM",
                NotificationManager.IMPORTANCE_HIGH
            )
            alarmChannel.description = "CHANNEL_ID_ALARM"
            val notiManager = getSystemService(NotificationManager::class.java)
            notiManager.createNotificationChannel(alarmChannel)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val infoStepChannel = NotificationChannel(
                Constant.CHANNEL_ID_INFO_STEP,
                "CHANNEL_ID_INFO_STEP",
                NotificationManager.IMPORTANCE_HIGH
            )
            infoStepChannel.description = "CHANNEL_ID_INFO_STEP"
            val infoManger = getSystemService(NotificationManager::class.java)
            infoManger.createNotificationChannel(infoStepChannel)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val updataChannel = NotificationChannel(
                Constant.CHANNEL_ID_UPDATE,
                "CHANNEL_ID_UPDATE",
                NotificationManager.IMPORTANCE_HIGH
            )
            updataChannel.description = "CHANNEL_ID_UPDATE"
            val updateManager = getSystemService(NotificationManager::class.java)
            updateManager.createNotificationChannel(updataChannel)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constant.CHANNEL_ID_STEP_GOAL,
                "CHANNEL_ID_STEP_GOAL",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "CHANNEL_ID_STEP_GOAL"

            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager?.createNotificationChannel(channel)
        }

    }
}
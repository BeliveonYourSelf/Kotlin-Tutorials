package com.steps.tracker.machine.analyzer.apputility

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.steps.tracker.machine.analyzer.services.InfoStepReceiver
import com.steps.tracker.machine.analyzer.services.StepGoalReceiver
import java.util.Calendar


class NotificationMangers {
    companion object {
        const val FULLSCREEN_REMINDER_NOTIFICATION_ID = 1
        const val FULLSCREEN_ALARM_NOTIFICATION_ID = 2
        const val FULLSCREEN_UPDATE_DATA_NOTIFICATION_ID = 3
        const val FULLSCREEN_REMINDER_NOTIFICATION_ID_INFO_STEP = 4
        const val FULLSCREEN_REMINDER_NOTIFICATION_ID_STEP_GOAL = 5

        fun sendNotification(context: Context, notificationId: Int, notification: Notification) {
            with(NotificationManagerCompat.from(context))
            {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return@with
                }
                notify(notificationId, notification)
            }
        }
        fun cancelNotification(context: Context, notificationId: Int, build: Notification) {
            with(NotificationManagerCompat.from(context)) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    return@with
                }
                cancel(notificationId)
            }
        }
        @SuppressLint("ScheduleExactAlarm")
        fun showNotificationStepGoal(context: Context, setHour:Int = 18, setMinute:Int =0){
            val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, StepGoalReceiver::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(context,
                    FULLSCREEN_REMINDER_NOTIFICATION_ID_STEP_GOAL, intent, PendingIntent.FLAG_IMMUTABLE)
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.set(Calendar.HOUR_OF_DAY, setHour)
            calendar.set(Calendar.MINUTE, setMinute)
            calendar.set(Calendar.SECOND, 0)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmMgr.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            } else
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
                }
                else {
                    alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
                }
        }



        fun showNotificationInfoSteps(context: Context, setHours: Int, setMinute: Int) {
            val alarmManager = context.getSystemService(AlarmManager::class.java)
            val intent = Intent(context, InfoStepReceiver::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(context,
                    FULLSCREEN_REMINDER_NOTIFICATION_ID_INFO_STEP, intent, PendingIntent.FLAG_IMMUTABLE)

            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.set(Calendar.HOUR_OF_DAY, setHours)
            calendar.set(Calendar.MINUTE, setMinute+1)
            calendar.set(Calendar.SECOND, 0)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )
                } else {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
                }
            }
        }
    }
}
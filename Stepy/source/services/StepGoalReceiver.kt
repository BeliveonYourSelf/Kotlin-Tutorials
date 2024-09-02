package com.steps.tracker.machine.analyzer.services


import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.steps.tracker.machine.analyzer.R
import com.steps.tracker.machine.analyzer.apputility.Constant
import com.steps.tracker.machine.analyzer.apputility.NotificationMangers
import com.steps.tracker.machine.analyzer.features.ui.splash.SplashActivity
import com.steps.tracker.machine.analyzer.prefrences.SharedPreferenceUtils

class StepGoalReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val contentIntent = Intent(context, SplashActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            12,
            contentIntent,
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_CANCEL_CURRENT
        )
        var content =
            if (SharedPreferenceUtils.dayStep >= SharedPreferenceUtils.targetStep)
                context.getString(R.string.pass_noti)
            else context.getString(R.string.not_pass_noti)

        val notificationBuilder = NotificationCompat.Builder(context, Constant.CHANNEL_ID_STEP_GOAL)
//            .setSmallIcon(R.mipmap.ic_app_launcher_q)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_CALL)
            .setFullScreenIntent(pendingIntent, true)
            .setContentTitle(content)
            .setContentIntent(pendingIntent)
        NotificationMangers.cancelNotification(
            context,
            NotificationMangers.FULLSCREEN_REMINDER_NOTIFICATION_ID_STEP_GOAL,
            notificationBuilder.build()
        )
        NotificationMangers.sendNotification(
            context,
            NotificationMangers.FULLSCREEN_REMINDER_NOTIFICATION_ID_STEP_GOAL,
            notificationBuilder.build()
        )
        NotificationMangers.showNotificationStepGoal(context)
    }
}
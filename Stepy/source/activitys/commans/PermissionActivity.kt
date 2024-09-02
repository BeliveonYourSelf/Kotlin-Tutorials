package com.steps.tracker.machine.analyzer.activitys.commans

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import com.steps.tracker.machine.analyzer.base.BaseBindingActivity
import com.steps.tracker.machine.analyzer.databinding.ActivityPermissionBinding

class PermissionActivity : BaseBindingActivity<ActivityPermissionBinding>() {
    val requestPermssions = registerForActivityResult(RequestPermission()) { isGrranted ->
        if (isGrranted) {
            openMainActivity()
        } else {
            openPermissionSettings()
        }
    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityPermissionBinding {
        return ActivityPermissionBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@PermissionActivity
    }

    override fun initView() {
        super.initView()
    }
    private fun TakePermission() {
        requestPermssions.launch(Manifest.permission.ACTIVITY_RECOGNITION)
    }
    private fun openPermissionSettings() {
        startActivity(Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", getActivityContext().packageName, null)
        })

    }

    private fun openMainActivity() {
        launchActivity(getActivityIntent<MainActivity> { })
    }

    override fun initViewListener() {
        super.initViewListener()
        setClickListener(binding.buttonContinue)
    }
    override fun onClick(v: View?) {
        when (v) {
            binding.buttonContinue -> TakePermission()

        }
    }

}
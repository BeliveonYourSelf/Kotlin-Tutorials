package com.steps.tracker.machine.analyzer.features.ui.signing

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.steps.tracker.machine.analyzer.base.BaseBindingActivity
import com.steps.tracker.machine.analyzer.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : BaseBindingActivity<ActivityForgotPasswordBinding>() {
    override fun setBinding(layoutInflater: LayoutInflater): ActivityForgotPasswordBinding {
        return ActivityForgotPasswordBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@ForgotPasswordActivity
    }

    override fun onClick(v: View?) {
        when(v)
        {

        }
    }

}
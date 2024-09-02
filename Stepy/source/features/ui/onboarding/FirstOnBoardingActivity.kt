package com.steps.tracker.machine.analyzer.features.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.steps.tracker.machine.analyzer.base.BaseBindingActivity
import com.steps.tracker.machine.analyzer.databinding.ActivityFirstOnBoardingBinding

class FirstOnBoardingActivity : BaseBindingActivity<ActivityFirstOnBoardingBinding>() {
    override fun setBinding(layoutInflater: LayoutInflater): ActivityFirstOnBoardingBinding {
        return ActivityFirstOnBoardingBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@FirstOnBoardingActivity
    }

    override fun initView() {
        super.initView()
    }

    override fun initViewListener() {
        super.initViewListener()
        setClickListener(binding.ivNext)
    }

    override fun onClick(v: View?) {
        when(v)
        {
            binding.ivNext ->{
                    launchActivity(getActivityIntent<SecoundOnBoardingActivity> {  })
            }
        }
    }

}
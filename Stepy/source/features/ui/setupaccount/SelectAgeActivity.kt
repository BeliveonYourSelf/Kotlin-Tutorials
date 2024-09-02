package com.steps.tracker.machine.analyzer.features.ui.setupaccount

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.steps.tracker.machine.analyzer.activitys.commans.MainActivity
import com.steps.tracker.machine.analyzer.base.BaseBindingActivity
import com.steps.tracker.machine.analyzer.databinding.ActivitySelectAgeBinding
import com.steps.tracker.machine.analyzer.prefrences.SharedPreferenceUtils

class SelectAgeActivity : BaseBindingActivity<ActivitySelectAgeBinding>() {
    override fun setBinding(layoutInflater: LayoutInflater): ActivitySelectAgeBinding {
        return ActivitySelectAgeBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@SelectAgeActivity
    }

    override fun initView() {
        super.initView()
    }

    override fun initViewListener() {
        super.initViewListener()
        setClickListener(binding.btnSkip,binding.btnNextStep,binding.pickAge)
    }

    override fun onClick(v: View?) {
        when(v)
        {
            binding.btnNextStep ->{
                SharedPreferenceUtils.age=binding.pickAge.value
                launchActivity(getActivityIntent<MainActivity> {  })
            }
            binding.btnSkip ->{
                launchActivity(getActivityIntent<MainActivity> {  })
            }

        }
    }

}
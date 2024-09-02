package com.ai.girl.friend.chatting.activity.comman

import android.view.LayoutInflater
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.ai.girl.friend.chatting.appprefrence.SharedPreferenceUtils
import com.ai.girl.friend.chatting.databinding.ActivityTweakPersonalityBinding
import com.universal.finance.tools.Base.BaseBindingActivity

class TweakPersonalityActivity : BaseBindingActivity<ActivityTweakPersonalityBinding>() {
    lateinit var OnBackPressedCallback : OnBackPressedCallback
    override fun setBinding(layoutInflater: LayoutInflater): ActivityTweakPersonalityBinding {
        return ActivityTweakPersonalityBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@TweakPersonalityActivity
    }

    override fun initView() {
        super.initView()
        OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                    getActivityContext().finish()
            }

        }
        onBackPressedDispatcher.addCallback(this,OnBackPressedCallback)

        with(binding) {
            if(SharedPreferenceUtils.selectCharToPersonality)
            {
                imgAvatar.setImageResource(SharedPreferenceUtils.selectedImageName)
            }else{
                imgAvatar.setImageResource(SharedPreferenceUtils.selectedImageName)
            }
        }

    }

    override fun initViewListener() {
        super.initViewListener()
        with(binding) {
            setClickListener(back,tvNext)
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when(v)
            {
                back ->{
                        onBackPressedDispatcher.onBackPressed()
                }
                tvNext ->{
                    if(SharedPreferenceUtils.selectCharToPersonality)
                    {
                        SharedPreferenceUtils.personalityToAiGirlName=true
                        launchActivity(getActivityIntent<GirlNameActivity> {  })
                    }
                }
            }
        }
    }

}
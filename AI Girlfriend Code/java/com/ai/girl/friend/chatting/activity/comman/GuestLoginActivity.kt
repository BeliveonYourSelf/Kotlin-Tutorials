package com.ai.girl.friend.chatting.activity.comman

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ai.girl.friend.chatting.activity.comman.MainActivity
import com.ai.girl.friend.chatting.appconstants.AppConstant
import com.ai.girl.friend.chatting.databinding.ActivityGuestLoginBinding
import com.universal.finance.tools.Base.BaseBindingActivity

class GuestLoginActivity : BaseBindingActivity<ActivityGuestLoginBinding>() {
    override fun setBinding(layoutInflater: LayoutInflater): ActivityGuestLoginBinding {
        return ActivityGuestLoginBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@GuestLoginActivity
    }

    override fun initViewListener() {
        super.initViewListener()
        with(binding) {
            setClickListener(conMain)
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when(v){
                conMain -> launchActivity(getActivityIntent<MainActivity> {
                    putBoolean(AppConstant.GUEST,true)
                })
            }
        }
    }

}
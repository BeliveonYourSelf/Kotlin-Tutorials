package com.ai.girl.friend.chatting.activity.comman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ai.girl.friend.chatting.R
import com.ai.girl.friend.chatting.databinding.ActivityFirstStartBinding
import com.universal.finance.tools.Base.BaseBindingActivity

class FirstStartActivity : BaseBindingActivity<ActivityFirstStartBinding>() {
    override fun setBinding(layoutInflater: LayoutInflater): ActivityFirstStartBinding {
        return ActivityFirstStartBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@FirstStartActivity
    }

    override fun initView() {
        super.initView()
    }

    override fun initViewListener() {
        super.initViewListener()
        with(binding) {
            setClickListener(tvNext)
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v) {
                tvNext ->{
                    launchActivity(getActivityIntent<SecondActivity> {  })
                }
            }
        }
    }

}
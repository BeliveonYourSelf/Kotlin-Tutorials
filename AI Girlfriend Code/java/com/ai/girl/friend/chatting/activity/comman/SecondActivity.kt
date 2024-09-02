package com.ai.girl.friend.chatting.activity.comman

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ai.girl.friend.chatting.databinding.ActivitySecondBinding
import com.universal.finance.tools.Base.BaseBindingActivity

class SecondActivity : BaseBindingActivity<ActivitySecondBinding>() {
    override fun setBinding(layoutInflater: LayoutInflater): ActivitySecondBinding {
        return ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@SecondActivity
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
                tvNext -> {
                    launchActivity(getActivityIntent<PrivacyPolicyActivity> { })
                }
            }
        }
    }

}

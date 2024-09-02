package com.ai.girl.friend.chatting.activity.comman

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ai.girl.friend.chatting.appconstants.AppConstant
import com.ai.girl.friend.chatting.appprefrence.SharedPreferenceUtils
import com.ai.girl.friend.chatting.databinding.ActivitySplashBinding
import com.universal.finance.tools.Base.BaseBindingActivity

class SplashActivity : BaseBindingActivity<ActivitySplashBinding>() {
    override fun setBinding(layoutInflater: LayoutInflater): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@SplashActivity
    }

    override fun initView() {
        super.initView()
        setData();
        Log.e(TAG, "initView: Url ===> ${AppConstant.baseUrl}", )
    }

    private fun setData() {
        Handler(Looper.getMainLooper())
            .postDelayed({
                if (SharedPreferenceUtils.isFirstTime) {
                    launchActivity(getActivityIntent<FirstStartActivity> { })
                } else {
                    if (SharedPreferenceUtils.isLogin) {
                        launchActivity(getActivityIntent<AIChatActivity> { })
                    } else {
                        if (SharedPreferenceUtils.isIntroComplete) {
                            SharedPreferenceUtils.guestChat=true
                            launchActivity(getActivityIntent<AIChatActivity> { })
                        } else {
                            launchActivity(getActivityIntent<GuestLoginActivity> { })
                        }
                    }
                }
            }, 2000)


    }


    override fun onClick(v: View?) {

    }
}
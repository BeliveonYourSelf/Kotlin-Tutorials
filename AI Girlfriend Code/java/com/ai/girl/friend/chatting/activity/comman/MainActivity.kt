package com.ai.girl.friend.chatting.activity.comman

import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ai.girl.friend.chatting.R
import com.ai.girl.friend.chatting.appconstants.AppConstant
import com.ai.girl.friend.chatting.appconstants.ShowProgress
import com.ai.girl.friend.chatting.appconstants.dismissProgress
import com.ai.girl.friend.chatting.appprefrence.SharedPreferenceUtils
import com.ai.girl.friend.chatting.databinding.ActivityMainBinding
import com.universal.finance.tools.Base.BaseBindingActivity

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    override fun setBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@MainActivity
    }

    override fun initView() {
        super.initView()
        val intentData = intent.extras
        val isGuest = intentData?.getBoolean(AppConstant.GUEST)
        showKeyboard(binding.editName)
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
                    hideKeyboard()
                    ShowProgress("Please Wait...",true, getActivityContext())
                    if (binding.editName.text.toString().isEmpty()) {
                        dismissProgress()
                        Toast.makeText(
                            getActivityContext(),
                            "Please enter your name",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }
                    SharedPreferenceUtils.userName=binding.editName.text.toString()
                    launchActivity(getActivityIntent<GenderActivity> {
                        putString(AppConstant.NAME,binding.editName.text.toString())
                        putBoolean(AppConstant.NAMETOPRONOUNS,true)
                    })


                }
            }


        }
    }

}
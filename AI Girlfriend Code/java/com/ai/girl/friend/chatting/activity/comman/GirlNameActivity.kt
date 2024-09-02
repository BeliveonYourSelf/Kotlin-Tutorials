package com.ai.girl.friend.chatting.activity.comman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ai.girl.friend.chatting.R
import com.ai.girl.friend.chatting.appprefrence.SharedPreferenceUtils
import com.ai.girl.friend.chatting.databinding.ActivityGirlNameBinding
import com.universal.finance.tools.Base.BaseBindingActivity

class GirlNameActivity : BaseBindingActivity<ActivityGirlNameBinding>() {
    override fun setBinding(layoutInflater: LayoutInflater): ActivityGirlNameBinding {
        return  ActivityGirlNameBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return  this@GirlNameActivity
    }

    override fun initView() {
        super.initView()
        with(binding) {
            if(SharedPreferenceUtils.personalityToAiGirlName)
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

                }
                tvNext ->{
                    if(editName.text.toString().isEmpty() )
                    {
                        editName.error="Please enter name"
                        Toast.makeText(
                            getActivityContext(),
                            "Please enter name",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }
                    SharedPreferenceUtils.gfName=editName.text.toString()
                    SharedPreferenceUtils.AiGirlToInterest=true

                    launchActivity(getActivityIntent<PersonalInterestActivity> {  })
                }
            }
        }
    }

}
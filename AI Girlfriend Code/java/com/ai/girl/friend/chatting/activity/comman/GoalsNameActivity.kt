package com.ai.girl.friend.chatting.activity.comman

import android.view.LayoutInflater
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ai.girl.friend.chatting.R
import com.ai.girl.friend.chatting.appprefrence.SharedPreferenceUtils
import com.ai.girl.friend.chatting.databinding.ActivityGoalsNameBinding
import com.universal.finance.tools.Base.BaseBindingActivity

class GoalsNameActivity : BaseBindingActivity<ActivityGoalsNameBinding>() {
    lateinit var OnBackPressedCallback: OnBackPressedCallback

    override fun setBinding(layoutInflater: LayoutInflater): ActivityGoalsNameBinding {
        return ActivityGoalsNameBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@GoalsNameActivity
    }

    override fun initView() {
        super.initView()
        OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                getActivityContext().finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, OnBackPressedCallback)
    }

    override fun initViewListener() {
        super.initViewListener()
        with(binding) {
            setClickListener(
                back,
                tvNext,
                ShareEmotions,
                ChatRandom,
                MakeFriend,
                HaveFun,
                TalkShameFree
            )
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v) {
                back -> {
                    onBackPressedDispatcher.onBackPressed()
                }

                tvNext -> {
                    if (SharedPreferenceUtils.InterestToGoal) {
                        SharedPreferenceUtils.GoalsToRelax = true
                        launchActivity(getActivityIntent<PeaceFulActivity> { })
                    }
                }

                ShareEmotions -> {
                    ShareEmotions.setBackgroundResource(R.drawable.bg_border_filled)
                    ShareEmotions.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.black))
                    ChatRandom.setBackgroundResource(R.drawable.bg_border)
                    ChatRandom.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    MakeFriend.setBackgroundResource(R.drawable.bg_border)
                    MakeFriend.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    HaveFun.setBackgroundResource(R.drawable.bg_border)
                    HaveFun.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    TalkShameFree.setBackgroundResource(R.drawable.bg_border)
                    TalkShameFree.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                }

                ChatRandom -> {
                    ChatRandom.setBackgroundResource(R.drawable.bg_border_filled)
                    ChatRandom.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.black))

                    ShareEmotions.setBackgroundResource(R.drawable.bg_border)
                    ShareEmotions.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    MakeFriend.setBackgroundResource(R.drawable.bg_border)
                    MakeFriend.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    HaveFun.setBackgroundResource(R.drawable.bg_border)
                    HaveFun.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    TalkShameFree.setBackgroundResource(R.drawable.bg_border)
                    TalkShameFree.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                }

                MakeFriend -> {
                    MakeFriend.setBackgroundResource(R.drawable.bg_border_filled)
                    MakeFriend.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.black))


                    ShareEmotions.setBackgroundResource(R.drawable.bg_border)
                    ShareEmotions.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    ChatRandom.setBackgroundResource(R.drawable.bg_border)
                    ChatRandom.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    HaveFun.setBackgroundResource(R.drawable.bg_border)
                    HaveFun.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    TalkShameFree.setBackgroundResource(R.drawable.bg_border)
                    TalkShameFree.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                }

                HaveFun -> {
                    HaveFun.setBackgroundResource(R.drawable.bg_border_filled)
                    HaveFun.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.black))

                    MakeFriend.setBackgroundResource(R.drawable.bg_border)
                    MakeFriend.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    ShareEmotions.setBackgroundResource(R.drawable.bg_border)
                    ShareEmotions.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    ChatRandom.setBackgroundResource(R.drawable.bg_border)
                    ChatRandom.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    TalkShameFree.setBackgroundResource(R.drawable.bg_border)
                    TalkShameFree.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                }

                TalkShameFree -> {
                    TalkShameFree.setBackgroundResource(R.drawable.bg_border_filled)
                    TalkShameFree.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.black))

                    HaveFun.setBackgroundResource(R.drawable.bg_border)
                    HaveFun.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    MakeFriend.setBackgroundResource(R.drawable.bg_border)
                    MakeFriend.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    ShareEmotions.setBackgroundResource(R.drawable.bg_border)
                    ShareEmotions.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                    ChatRandom.setBackgroundResource(R.drawable.bg_border)
                    ChatRandom.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))

                }


            }
        }
    }

}
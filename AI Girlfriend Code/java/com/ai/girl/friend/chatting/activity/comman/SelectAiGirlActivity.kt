package com.ai.girl.friend.chatting.activity.comman

import android.view.LayoutInflater
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.ai.girl.friend.chatting.R
import com.ai.girl.friend.chatting.adapters.ImageAdapters
import com.ai.girl.friend.chatting.appconstants.AppConstant
import com.ai.girl.friend.chatting.appprefrence.SharedPreferenceUtils
import com.ai.girl.friend.chatting.databinding.ActivitySelectAiGirlBinding
import com.ai.girl.friend.chatting.interfaces.onItemSelect
import com.universal.finance.tools.Base.BaseBindingActivity

class SelectAiGirlActivity : BaseBindingActivity<ActivitySelectAiGirlBinding>() {
    var images: IntArray = intArrayOf(
        R.drawable.girl_av2,
        R.drawable.girl_av3,
        R.drawable.girl_av4,
        R.drawable.girl_av5,
        R.drawable.girl_av6,
        R.drawable.girl_av7,
        R.drawable.girl_av8,
        R.drawable.girl_av9,
        R.drawable.girl_av10,
        R.drawable.girl_av11,
        R.drawable.boy_av1,
        R.drawable.boy_av2,
        R.drawable.boy_av3,
        R.drawable.boy_av4
    )
    lateinit var imageAdapters: ImageAdapters
    lateinit var onBackPressedCallback: OnBackPressedCallback
    var isPronounce: Boolean = false
    override fun setBinding(layoutInflater: LayoutInflater): ActivitySelectAiGirlBinding {
        return ActivitySelectAiGirlBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@SelectAiGirlActivity
    }

    override fun initView() {
        super.initView()
        val intentData = intent.extras
        isPronounce = intentData!!.getBoolean(AppConstant.PRONOUNSTOSELECTCHARACTER, false)
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                getActivityContext().finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        initRecyclerView();
    }

    private fun initRecyclerView() {
        with(binding) {
            imageAdapters = ImageAdapters(getActivityContext(), images, object : onItemSelect {
                override fun onItemClick(pos: Int) {

                }
            })
            recycler.adapter = imageAdapters
        }
    }

    fun setSelectedImage(iSelected: Int) {
        binding.imgAvatar.setImageResource(images[iSelected])
        SharedPreferenceUtils.selectedImageName = images[iSelected]

    }

    override fun initViewListener() {
        super.initViewListener()
        with(binding) {
            setClickListener(back, btnNext)
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v) {
                btnNext -> {
                    if (SharedPreferenceUtils.pronounsToSelectCharacter) {
                        SharedPreferenceUtils.selectCharToPersonality = true
                        launchActivity(getActivityIntent<TweakPersonalityActivity> { })
                    }
                }

                back -> {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }
    }

}
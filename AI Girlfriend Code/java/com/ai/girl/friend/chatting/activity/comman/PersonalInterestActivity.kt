package com.ai.girl.friend.chatting.activity.comman

import android.view.LayoutInflater
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.ai.girl.friend.chatting.R
import com.ai.girl.friend.chatting.adapters.PersonalInterestAdapter
import com.ai.girl.friend.chatting.appprefrence.SharedPreferenceUtils
import com.ai.girl.friend.chatting.databinding.ActivityPersonalInterestBinding
import com.ai.girl.friend.chatting.datamodels.PersonalInterestModel
import com.ai.girl.friend.chatting.interfaces.onItemSelect
import com.universal.finance.tools.Base.BaseBindingActivity

class PersonalInterestActivity : BaseBindingActivity<ActivityPersonalInterestBinding>() {
    lateinit var arrayList: List<PersonalInterestModel>
    lateinit var personalInterestAdapter: PersonalInterestAdapter
    lateinit var onBackPressedCallback: OnBackPressedCallback
    override fun setBinding(layoutInflater: LayoutInflater): ActivityPersonalInterestBinding {
        return ActivityPersonalInterestBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@PersonalInterestActivity
    }

    override fun initView() {
        super.initView()
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                getActivityContext().finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        initData();
    }

    private fun initData() {
        arrayList = mutableListOf(
            PersonalInterestModel(R.drawable.garden, "Gardening"),
            PersonalInterestModel(R.drawable.mountain, "Hiking"),
            PersonalInterestModel(R.drawable.cat, "Cat"),
            PersonalInterestModel(R.drawable.politics, "Politics"),
            PersonalInterestModel(R.drawable.games, "Games"),
            PersonalInterestModel(R.drawable.wine, "Wine"),
            PersonalInterestModel(R.drawable.music, "Music"),
            PersonalInterestModel(R.drawable.instagram, "Instagram"),
            PersonalInterestModel(R.drawable.swim, "Swimming"),
            PersonalInterestModel(R.drawable.garden, "Outdoors"),
            PersonalInterestModel(R.drawable.tea, "Tea"),
            PersonalInterestModel(R.drawable.beer, "Beer"),
            PersonalInterestModel(R.drawable.walk, "Walk"),
            PersonalInterestModel(R.drawable.running, "Running"),
            PersonalInterestModel(R.drawable.astrology, "Astrology"),
        )
        personalInterestAdapter = PersonalInterestAdapter(getActivityContext(), arrayList,
            object : onItemSelect {
                override fun onItemClick(pos: Int) {

                }
            })
        binding.recycler.adapter = personalInterestAdapter
    }

    override fun initViewListener() {
        super.initViewListener()
        with(binding) {
            setClickListener(back, tvNext)
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v) {
                back -> {
                    onBackPressedDispatcher.onBackPressed()
                }

                tvNext -> {
                        if(SharedPreferenceUtils.AiGirlToInterest)
                        {
                            SharedPreferenceUtils.InterestToGoal=true
                            launchActivity(getActivityIntent<GoalsNameActivity> {  })
                        }
                }
            }
        }
    }

}
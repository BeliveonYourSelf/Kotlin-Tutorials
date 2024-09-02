package com.steps.tracker.machine.analyzer.features.ui.setupaccount

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.steps.tracker.machine.analyzer.R
import com.steps.tracker.machine.analyzer.base.BaseBindingActivity
import com.steps.tracker.machine.analyzer.databinding.ActivitySelectGenderBinding
import com.steps.tracker.machine.analyzer.prefrences.SharedPreferenceUtils

class SelectGenderActivity : BaseBindingActivity<ActivitySelectGenderBinding>() {
    private var isChooseSex = -1
    val MALE = "MALE"
    val FEMALE = "FEMALE"
    override fun setBinding(layoutInflater: LayoutInflater): ActivitySelectGenderBinding {
        return ActivitySelectGenderBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@SelectGenderActivity
    }

    override fun initView() {
        super.initView()
        when (SharedPreferenceUtils.selectSex) {
            0 -> {
                showClickImgSex(FEMALE)
            }

            1 -> {
                showClickImgSex(MALE)
            }
        }
    }

    private fun showClickImgSex(from: String) {
        when (from) {
            MALE -> {
                isChooseSex = 1
                binding.imgWomen.setImageResource(R.drawable.img_women_false)
                binding.imgMan.setImageResource(R.drawable.img_man_true)
                binding.tvMale.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.colorPrimary))
                binding.tvFemale.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.black))
            }

            FEMALE -> {
                isChooseSex = 0
                binding.imgWomen.setImageResource(R.drawable.img_women_true)
                binding.imgMan.setImageResource(R.drawable.img_man_false)
                binding.tvFemale.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.colorPrimary))
                binding.tvMale.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.black))
            }
        }


    }

    override fun initViewListener() {
        super.initViewListener()
        setClickListener(binding.imgMan, binding.imgWomen, binding.btnNextStep)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnNextStep -> {
                when (isChooseSex) {
                    0 -> {
                        SharedPreferenceUtils.selectSex = 0
                        launchActivity(getActivityIntent<SelectHowTallActivity> { })
                    }

                    1 -> {
                        SharedPreferenceUtils.selectSex = 1
                        launchActivity(getActivityIntent<SelectHowTallActivity> { })
                    }

                    else -> {
                        SharedPreferenceUtils.selectSex = -1
                    }
                }
            }

            binding.imgMan -> {
                showClickImgSex(MALE)

            }

            binding.imgWomen -> {
                showClickImgSex(FEMALE)
            }

            binding.btnSkip -> {
                SharedPreferenceUtils.selectSex = -1
            }
        }
    }

}
package com.steps.tracker.machine.analyzer.features.ui.setupaccount

import DateUtils
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.steps.tracker.machine.analyzer.R
import com.steps.tracker.machine.analyzer.base.BaseBindingActivity
import com.steps.tracker.machine.analyzer.databases.models.Weights
import com.steps.tracker.machine.analyzer.databinding.ActivitySelectWeightBinding
import com.steps.tracker.machine.analyzer.prefrences.SharedPreferenceUtils

class SelectWeightActivity : BaseBindingActivity<ActivitySelectWeightBinding>() {
    private var isChooseUnit = SharedPreferenceUtils.unit
    private val KG = "KG"
    private val LBS = "LBS"
    override fun setBinding(layoutInflater: LayoutInflater): ActivitySelectWeightBinding {
        return ActivitySelectWeightBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@SelectWeightActivity
    }

    override fun initView() {
        super.initView()
        setValue()
        if (SharedPreferenceUtils.unit) onSelectWeight(KG)
        else onSelectWeight(LBS)

    }

    private fun setValue() {
        when (SharedPreferenceUtils.unit) {
            true -> {
                if (SharedPreferenceUtils.weight0_temporary == 0f) {
                    binding.pickWeightInt.value = 70
                    binding.pickWeightDecimal.value = 0
                } else {
                    val parts = SharedPreferenceUtils.weight0_temporary.toString().split(".")
                    val numInt = parts[0]
                    val numDecimal = parts[1]
                    binding.pickWeightInt.value = numInt.toInt()
                    binding.pickWeightDecimal.value = numDecimal.toInt()
                }
            }

            false -> {
                if (SharedPreferenceUtils.weight1_temporary == 0f) {
                    binding.pickWeightInt.value = 350
                    binding.pickWeightDecimal.value = 0

                } else {
                    val parts = SharedPreferenceUtils.weight1_temporary.toString().split(".")
                    val numInt = parts[0]
                    val numDecimal = parts[1]
                    binding.pickWeightInt.value = numInt.toInt()
                    binding.pickWeightDecimal.value = numDecimal.toInt()
                }
            }
        }
    }

    private fun onSelectWeight(from :String) {
        when(from)
        {
            KG ->{
                isChooseUnit = true
                binding.kg.setBackgroundResource(R.drawable.bg_unit)
                binding.lbs.setBackgroundResource(R.drawable.bg_unselected_unit)
                binding.kg.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.white))
                binding.lbs.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.white))

                binding.pickWeightInt.maxValue = 300
                binding.pickWeightInt.minValue = 15
                if (SharedPreferenceUtils.weight0_temporary == 0f) {
                    binding.pickWeightInt.value = 70
                    binding.pickWeightDecimal.value = 0
                } else {
                    val parts = SharedPreferenceUtils.weight0_temporary.toString().split(".")
                    val numInt = parts[0]
                    val numDecimal = parts[1]
                    binding.pickWeightInt.value = numInt.toInt()
                    binding.pickWeightDecimal.value = numDecimal.toInt()
                    if (numInt.toInt() == 300) {
                        binding.pickWeightDecimal.value = 0
                    }
                }

            }
            LBS ->{
                isChooseUnit = false
                binding.lbs.setBackgroundResource(R.drawable.bg_unit)
                binding.kg.setBackgroundResource(R.drawable.bg_unselected_unit)
                binding.kg.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.white))
                binding.lbs.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.white))

                binding.pickWeightInt.maxValue = 661
                binding.pickWeightInt.minValue = 33
                if (SharedPreferenceUtils.weight1_temporary == 0f) {
                    binding.pickWeightInt.value = 350
                    binding.pickWeightDecimal.value = 0
                } else {
                    val parts = SharedPreferenceUtils.weight1_temporary.toString().split(".")
                    val numInt = parts[0]
                    val numDecimal = parts[1].substring(0, 1)
                    binding.pickWeightInt.value = numInt.toInt()
                    binding.pickWeightDecimal.value = numDecimal.toInt()
                    if (numInt.toInt() == 661) {
                        binding.pickWeightDecimal.maxValue = 0
                    }
                }
            }
        }

    }

    override fun initViewListener() {
        super.initViewListener()
        setClickListener(binding.kg, binding.lbs, binding.btnNextStep, binding.btnSkip)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.kg -> {
                onSelectWeight(KG)
            }

            binding.lbs -> {
                onSelectWeight(LBS)
            }

            binding.btnNextStep -> {
                if (isChooseUnit) {
                    SharedPreferenceUtils.weight =
                        "${binding.pickWeightInt.value}.${binding.pickWeightDecimal.value}".toFloat()
                }else{
                    SharedPreferenceUtils.weight="${binding.pickWeightInt.value}.${binding.pickWeightDecimal.value}".toFloat()
                    SharedPreferenceUtils.weight=(SharedPreferenceUtils.weight/2.20462).toFloat()
                }
                SharedPreferenceUtils.unit = isChooseUnit
                val weights = Weights()
                weights.weight=SharedPreferenceUtils.weight
                weights.updateTime= DateUtils.getDateFromTimeMillis(System.currentTimeMillis())

                launchActivity(getActivityIntent<SelectAgeActivity> {  })

            }

            binding.btnSkip -> {

            }
        }
    }

}
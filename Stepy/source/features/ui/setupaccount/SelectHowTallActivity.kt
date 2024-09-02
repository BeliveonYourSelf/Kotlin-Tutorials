package com.steps.tracker.machine.analyzer.features.ui.setupaccount

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.shawnlin.numberpicker.NumberPicker
import com.steps.tracker.machine.analyzer.R
import com.steps.tracker.machine.analyzer.base.BaseBindingActivity
import com.steps.tracker.machine.analyzer.databinding.ActivitySelectHowTallBinding
import com.steps.tracker.machine.analyzer.prefrences.SharedPreferenceUtils

class SelectHowTallActivity : BaseBindingActivity<ActivitySelectHowTallBinding>() {
    private var isChooseUnit = SharedPreferenceUtils.unit
    private val FT = "FT"
    private val CM = "CM"
    override fun setBinding(layoutInflater: LayoutInflater): ActivitySelectHowTallBinding {
        return ActivitySelectHowTallBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@SelectHowTallActivity
    }

    override fun initView() {
        super.initView()
        setValues()
        if (SharedPreferenceUtils.unit) onSelectType(CM)
        else onSelectType(FT)

        binding.pickHeightInt.setOnValueChangedListener { picker, oldVal, newVal ->
            if (!isChooseUnit) {
                when (newVal) {
                    8 -> {
                        binding.pickHeightDecimal.maxValue = 2
                        binding.pickHeightDecimal.minValue = 0
                    }

                    0 -> {
                        binding.pickHeightDecimal.minValue = 8
                        binding.pickHeightDecimal.maxValue = 9
                    }

                    else -> {
                        binding.pickHeightDecimal.maxValue = 9
                        binding.pickHeightDecimal.minValue = 0
                    }
                }
            }
            if (isChooseUnit) {
                SharedPreferenceUtils.height0_temporary =
                    "${binding.pickHeightInt.value}.${binding.pickHeightDecimal.value}".toFloat()
                if (newVal == 250) {
                    binding.pickHeightDecimal.maxValue = 0
                } else {
                    binding.pickHeightDecimal.maxValue = 9
                    binding.pickHeightDecimal.minValue = 0
                }
            }
        }
        binding.pickHeightDecimal.setOnValueChangedListener { picker, oldVal, newVal ->
            if (!isChooseUnit) {
                SharedPreferenceUtils.height1_temporary =
                    "${binding.pickHeightInt.value}.${binding.pickHeightDecimal.value}".toFloat()

            }
            if (isChooseUnit) {
                SharedPreferenceUtils.height0_temporary =
                    "${binding.pickHeightInt.value}.${binding.pickHeightDecimal.value}".toFloat()
            }
        }

    }

    private fun onSelectType(from :String) {
        when(from)
        {
            CM ->{
                isChooseUnit = true
                binding.cm.setBackgroundResource(R.drawable.bg_unit)
                binding.ft.setBackgroundResource(R.drawable.bg_unselected_unit)
                binding.cm.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                binding.ft.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))

                binding.pickHeightInt.maxValue = 250
                binding.pickHeightInt.minValue = 25
                binding.pickHeightDecimal.maxValue = 9
                binding.pickHeightDecimal.minValue = 0

                if(SharedPreferenceUtils.height0_temporary == 0f)
                {
                    binding.pickHeightInt.value = 170
                    binding.pickHeightDecimal.value = 0
                }else{
                    val parts = SharedPreferenceUtils.height0_temporary.toString().split(".")
                    val numInt =parts[0]
                    val numDecimal =parts[1]
                    binding.pickHeightInt.value = numInt.toInt()
                    binding.pickHeightInt.value = numDecimal.toInt()
                    if(numInt.toInt() ==250) binding.pickHeightDecimal.maxValue=0
                }
            }
            FT ->{
                isChooseUnit = false
                binding.ft.setBackgroundResource(R.drawable.bg_unit)
                binding.cm.setBackgroundResource(R.drawable.bg_unselected_unit)
                binding.ft.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))
                binding.cm.setTextColor(ContextCompat.getColor(getActivityContext(),R.color.white))


                binding.pickHeightInt.maxValue = 9
                binding.pickHeightInt.minValue = 0
                binding.pickHeightDecimal.maxValue = 8
                binding.pickHeightDecimal.minValue = 0

                if(SharedPreferenceUtils.height1_temporary == 0f)
                {
                    binding.pickHeightInt.value =4
                    binding.pickHeightDecimal.value = 0
                }else{
                    val parts = SharedPreferenceUtils.height1_temporary.toString().split(".")
                    val numInt = parts[0]
                    val numDecimal = parts[1]
                    binding.pickHeightInt.value = numInt.toInt()
                    binding.pickHeightDecimal.value = numDecimal.toInt()
                    if(numInt.toInt() == 0){
                        binding.pickHeightDecimal.maxValue = 9
                        binding.pickHeightDecimal.minValue = 8
                    }
                    if(numInt.toInt() == 8){
                        binding.pickHeightDecimal.maxValue = 2
                        binding.pickHeightDecimal.minValue = 0
                    }
                }
            }
        }
    }

    private fun setValues() {
        when (SharedPreferenceUtils.unit) {
            true -> {
                if (SharedPreferenceUtils.height0_temporary == 0f) {
                    binding.pickHeightInt.value = 170
                    binding.pickHeightDecimal.value = 0
                } else {
                    val parts = SharedPreferenceUtils.height0_temporary.toString().split(".")
                    val numInt = parts[0]
                    val numDecimal = parts[1]
                    binding.pickHeightInt.value = numInt.toInt()
                    binding.pickHeightDecimal.value = numDecimal.toInt()
                }
            }

            false -> {
                if (SharedPreferenceUtils.height1_temporary == 0f) {
                    binding.pickHeightInt.value = 4
                    binding.pickHeightDecimal.value = 0
                } else {
                    val parts = (SharedPreferenceUtils.height1_temporary).toString().split(".")
                    val numInt = parts[0]
                    val numDecimal = parts[1]
                    binding.pickHeightInt.value = numInt.toInt()
                    binding.pickHeightDecimal.value = numDecimal.toInt()
                }
            }

        }
    }

    override fun initViewListener() {
        super.initViewListener()
        setClickListener(binding.ft, binding.cm,binding.btnNextStep,binding.btnSkip)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.ft -> {
                onSelectType(FT)
            }

            binding.cm -> {
                onSelectType(CM)
            }

            binding.btnNextStep -> {
                if (isChooseUnit) {
                    SharedPreferenceUtils.height =
                        "${binding.pickHeightInt.value}.${binding.pickHeightDecimal.value}".toFloat()
                }
                if (!isChooseUnit) {
                    SharedPreferenceUtils.height =
                        "${binding.pickHeightInt.value}.${binding.pickHeightDecimal.value}".toFloat()
                    SharedPreferenceUtils.height = (SharedPreferenceUtils.height * 30.48).toFloat()
                }


                SharedPreferenceUtils.unit = isChooseUnit
                launchActivity(getActivityIntent<SelectWeightActivity> {  })
            }

            binding.btnSkip -> {

            }
        }
    }

}
package com.steps.tracker.machine.analyzer.dialogs

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.steps.tracker.machine.analyzer.databinding.DialogStepGoalBinding
import com.steps.tracker.machine.analyzer.interfaces.OnClickBottomSheetListener
import com.steps.tracker.machine.analyzer.prefrences.SharedPreferenceUtils

class StepGoalBottomDialog( context: Context, val onDiallogClick: OnClickBottomSheetListener) : BottomSheetDialog(context) {
    val binding = DialogStepGoalBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val values = arrayListOf<Int>()
        for (i in 500..4000 step 500) {
            values.add(i)
        }
        val displayValues = values.map { it.toString() }.toTypedArray()
        for (i in 0 until binding.pickGoal.childCount) {
            val child = binding.pickGoal.getChildAt(i)
            if (child is EditText) {
//                child.typeface = context.getFontCompat(R.font.mukta_semi_bold)
            }

        }
        binding.pickGoal.minValue = 0
        binding.pickGoal.maxValue = values.size - 1
        binding.pickGoal.displayedValues = displayValues

        for (i in 0 until values.size) {
            if (values[i] == SharedPreferenceUtils.targetStep.toInt()) {
                binding.pickGoal.value = i
            }
        }
        binding.btnClose.setOnClickListener {
            dismiss()
        }

        binding.btnSave.setOnClickListener {
            SharedPreferenceUtils.targetStep = values[binding.pickGoal.value].toLong()
            onDiallogClick.onClickSaveFrom()
            checkHideBottomSheet()
        }
    }

    fun checkHideBottomSheet() {
        if (isShowing) {
            dismiss()
        }
    }

    fun checkShowBottomSheet() {
        if (!isShowing) {
            show()
        }
    }
}
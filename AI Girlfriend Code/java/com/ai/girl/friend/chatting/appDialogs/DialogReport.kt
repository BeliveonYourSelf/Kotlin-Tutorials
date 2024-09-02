package com.ai.girl.friend.chatting.appDialogs

import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.ai.girl.friend.chatting.R
import com.ai.girl.friend.chatting.activity.comman.AIChatActivity
import com.ai.girl.friend.chatting.databinding.DialogReportBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class DialogReport( context: Context) :
    BottomSheetDialog(context, R.style.AppBottomSheetDialogTheme) {
    val binding = DialogReportBinding.inflate(layoutInflater)
    lateinit var dialoReportOther: ReportOther
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setCancelable(true)
        binding.btnContinue.isEnabled = false
        with(binding) {
            offensive.setOnClickListener {
                offensive.setBackgroundResource(R.drawable.bg_border_filled)
                offensive.setTextColor(ContextCompat.getColor(context, R.color.black))
                gender.setBackgroundResource(R.drawable.bg_border)
                gender.setTextColor(ContextCompat.getColor(context, R.color.white))
                nameMistake.setBackgroundResource(R.drawable.bg_border)
                nameMistake.setTextColor(ContextCompat.getColor(context, R.color.white))
                memory.setBackgroundResource(R.drawable.bg_border)
                memory.setTextColor(ContextCompat.getColor(context, R.color.white))
                repeatedQuestion.setBackgroundResource(R.drawable.bg_border)
                repeatedQuestion.setTextColor(ContextCompat.getColor(context, R.color.white))
                other.setBackgroundResource(R.drawable.bg_border)
                other.setTextColor(ContextCompat.getColor(context, R.color.white))
                btnContinue.isEnabled = true
                btnContinue.setTextColor(ContextCompat.getColor(context, R.color.white))
                btnContinue.text = "Report"
                AIChatActivity.isOtherClicked = false

            }

            gender.setOnClickListener {
                offensive.setBackgroundResource(R.drawable.bg_border)
                offensive.setTextColor(ContextCompat.getColor(context, R.color.black))
                gender.setBackgroundResource(R.drawable.bg_border_filled)
                gender.setTextColor(ContextCompat.getColor(context, R.color.white))
                nameMistake.setBackgroundResource(R.drawable.bg_border)
                nameMistake.setTextColor(ContextCompat.getColor(context, R.color.white))
                memory.setBackgroundResource(R.drawable.bg_border)
                memory.setTextColor(ContextCompat.getColor(context, R.color.white))
                repeatedQuestion.setBackgroundResource(R.drawable.bg_border)
                repeatedQuestion.setTextColor(ContextCompat.getColor(context, R.color.white))
                other.setBackgroundResource(R.drawable.bg_border)
                other.setTextColor(ContextCompat.getColor(context, R.color.white))
                btnContinue.isEnabled = true
                btnContinue.setTextColor(ContextCompat.getColor(context, R.color.white))
                btnContinue.text = "Report"
                AIChatActivity.isOtherClicked = false
            }
            nameMistake.setOnClickListener {
                offensive.setBackgroundResource(R.drawable.bg_border)
                offensive.setTextColor(ContextCompat.getColor(context, R.color.white))
                gender.setBackgroundResource(R.drawable.bg_border)
                gender.setTextColor(ContextCompat.getColor(context, R.color.white))
                nameMistake.setBackgroundResource(R.drawable.bg_border_filled)
                nameMistake.setTextColor(ContextCompat.getColor(context, R.color.black))
                memory.setBackgroundResource(R.drawable.bg_border)
                memory.setTextColor(ContextCompat.getColor(context, R.color.white))
                repeatedQuestion.setBackgroundResource(R.drawable.bg_border)
                repeatedQuestion.setTextColor(ContextCompat.getColor(context, R.color.white))
                other.setBackgroundResource(R.drawable.bg_border)
                other.setTextColor(ContextCompat.getColor(context, R.color.white))
                btnContinue.isEnabled = true
                btnContinue.setTextColor(ContextCompat.getColor(context, R.color.white))
                btnContinue.text = "Report"
                AIChatActivity.isOtherClicked = false
            }

            memory.setOnClickListener {
                offensive.setBackgroundResource(R.drawable.bg_border)
                offensive.setTextColor(ContextCompat.getColor(context, R.color.white))
                gender.setBackgroundResource(R.drawable.bg_border)
                gender.setTextColor(ContextCompat.getColor(context, R.color.white))
                nameMistake.setBackgroundResource(R.drawable.bg_border)
                nameMistake.setTextColor(ContextCompat.getColor(context, R.color.white))
                memory.setBackgroundResource(R.drawable.bg_border_filled)
                memory.setTextColor(ContextCompat.getColor(context, R.color.black))
                repeatedQuestion.setBackgroundResource(R.drawable.bg_border)
                repeatedQuestion.setTextColor(ContextCompat.getColor(context, R.color.white))
                other.setBackgroundResource(R.drawable.bg_border)
                other.setTextColor(ContextCompat.getColor(context, R.color.white))
                btnContinue.isEnabled = true
                btnContinue.setTextColor(ContextCompat.getColor(context, R.color.white))
                btnContinue.text = "Report"
                AIChatActivity.isOtherClicked = false
            }

            repeatedQuestion.setOnClickListener {
                offensive.setBackgroundResource(R.drawable.bg_border)
                offensive.setTextColor(ContextCompat.getColor(context, R.color.white))
                gender.setBackgroundResource(R.drawable.bg_border)
                gender.setTextColor(ContextCompat.getColor(context, R.color.white))
                nameMistake.setBackgroundResource(R.drawable.bg_border)
                nameMistake.setTextColor(ContextCompat.getColor(context, R.color.white))
                memory.setBackgroundResource(R.drawable.bg_border)
                memory.setTextColor(ContextCompat.getColor(context, R.color.white))
                repeatedQuestion.setBackgroundResource(R.drawable.bg_border_filled)
                repeatedQuestion.setTextColor(ContextCompat.getColor(context, R.color.black))
                other.setBackgroundResource(R.drawable.bg_border)
                other.setTextColor(ContextCompat.getColor(context, R.color.white))
                btnContinue.isEnabled = true
                btnContinue.setTextColor(ContextCompat.getColor(context, R.color.white))
                btnContinue.text = "Report"
                AIChatActivity.isOtherClicked = false
            }

            other.setOnClickListener {
                offensive.setBackgroundResource(R.drawable.bg_border)
                offensive.setTextColor(ContextCompat.getColor(context, R.color.white))
                gender.setBackgroundResource(R.drawable.bg_border)
                gender.setTextColor(ContextCompat.getColor(context, R.color.white))
                nameMistake.setBackgroundResource(R.drawable.bg_border)
                nameMistake.setTextColor(ContextCompat.getColor(context, R.color.white))
                memory.setBackgroundResource(R.drawable.bg_border)
                memory.setTextColor(ContextCompat.getColor(context, R.color.white))
                repeatedQuestion.setBackgroundResource(R.drawable.bg_border)
                repeatedQuestion.setTextColor(ContextCompat.getColor(context, R.color.white))
                other.setBackgroundResource(R.drawable.bg_border_filled)
                other.setTextColor(ContextCompat.getColor(context, R.color.black))
                btnContinue.isEnabled = true
                btnContinue.setTextColor(ContextCompat.getColor(context, R.color.white))
                btnContinue.text = "Report"
                AIChatActivity.isOtherClicked = false
            }
            btnContinue.setOnClickListener {
                if (AIChatActivity.isOtherClicked) {
                    if (isShowing) {
                        dismiss()
                        return@setOnClickListener
                    }
                    dismiss()
                    dialoReportOther = ReportOther(context)
                    dialoReportOther.show()
                }

            }


        }
    }
}
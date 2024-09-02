package com.ai.girl.friend.chatting.appDialogs

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.ai.girl.friend.chatting.R
import com.ai.girl.friend.chatting.databinding.ReportOtherBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ReportOther( context: Context) :
    BottomSheetDialog(context, R.style.AppBottomSheetDialogTheme) {
    val binding = ReportOtherBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setCancelable(true)

        with(binding) {
            btnContinue.setOnClickListener {
                if(binding.etName.text.isEmpty())
                {
                    Toast.makeText(context, "Please submit your issue", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                Toast.makeText(context, "Message has been reported", Toast.LENGTH_SHORT).show()
                dismiss()

            }
            mClose.setOnClickListener {
                if(isShowing) dismiss()
            }
        }
    }
}
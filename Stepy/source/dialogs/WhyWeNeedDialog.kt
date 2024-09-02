package com.steps.tracker.machine.analyzer.dialogs

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.steps.tracker.machine.analyzer.databinding.WhyWeNeedDialogBinding

class WhyWeNeedDialog( context: Context, callback : () ->Unit): BottomSheetDialog(context) {
    val binding = WhyWeNeedDialogBinding.inflate(layoutInflater)

    init {
        setContentView(binding.root)
        setCanceledOnTouchOutside(false)
        setCancelable(false)

        binding.btnGrant.setOnClickListener {
            callback()
            dismiss()
        }
    }
}
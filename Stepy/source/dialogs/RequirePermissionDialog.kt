package com.steps.tracker.machine.analyzer.dialogs

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.steps.tracker.machine.analyzer.databinding.DialogRequirePermissionBinding

class RequirePermissionDialog( context: Context, callback : () ->Unit) : BottomSheetDialog(context) {
    val binding = DialogRequirePermissionBinding.inflate(layoutInflater)
    init {
        setContentView(binding.root)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        binding.btnGrant.setOnClickListener {
            callback()
            dismiss()
        }
    }
}
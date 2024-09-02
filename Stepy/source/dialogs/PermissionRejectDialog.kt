package com.steps.tracker.machine.analyzer.dialogs

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.steps.tracker.machine.analyzer.databinding.DialogPermissionRejectBinding

class PermissionRejectDialog( context: Context, callback : () -> Unit) : BottomSheetDialog(context) {
    val binding = DialogPermissionRejectBinding.inflate(layoutInflater)
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
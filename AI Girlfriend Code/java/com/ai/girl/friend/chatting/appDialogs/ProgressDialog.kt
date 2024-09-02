package com.ai.girl.friend.chatting.appDialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.ai.girl.friend.chatting.appconstants.gone
import com.ai.girl.friend.chatting.appconstants.visiable
import com.ai.girl.friend.chatting.databinding.ProgressLoadingBinding

class ProgressDialog(context: Context, val message: Any,val isTittleShow: Boolean) : Dialog(context) {
    val binding = ProgressLoadingBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        if (isTittleShow) {
            binding.textView.visiable()
        } else {
            binding.textView.gone()
        }
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
    }
}
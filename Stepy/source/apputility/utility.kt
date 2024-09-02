package com.steps.tracker.machine.analyzer.apputility

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.steps.tracker.machine.analyzer.databinding.ProgressDialogBinding
import com.steps.tracker.machine.analyzer.globals.ForestApplication

fun showToast(mess: Any) {
    Toast.makeText(ForestApplication.appContext, mess.toString(), Toast.LENGTH_LONG).show()
}

fun logD(value: Any) {
    Log.d("abcd", "$value")
}


fun formatNumbers(inputNums: Float): String {
    val formattedNumber = String.format("%.2f", inputNums).replace(",", ".")
    return if (formattedNumber.endsWith(".00")) {
        formattedNumber.replace(".00", "")
    } else if (formattedNumber.endsWith("0")) {
        formattedNumber.substring(0, formattedNumber.length - 1)
    } else {
        formattedNumber
    }
}

fun formatNumbers(inputNums: Double): String {
    val formattedNumber = String.format("%.2f", inputNums).replace(",", ".")
    return if (formattedNumber.endsWith(".00")) {
        formattedNumber.replace(".00", "")
    } else if (formattedNumber.endsWith("0")) {
        formattedNumber.substring(0, formattedNumber.length - 1)
    } else {
        formattedNumber
    }
}

val progressdialog = Dialog(ForestApplication.appContext)
fun showProgress(message: Any, activity: Activity) {
    val myDialogBinding = ProgressDialogBinding.inflate(activity.layoutInflater)
    progressdialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    progressdialog.window!!.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT
    )
    progressdialog.setCancelable(false)
    myDialogBinding.tvMessage.text = message.toString()
    progressdialog.show()

}

fun dismissProgress() {
    if (progressdialog.isShowing) {
        progressdialog.dismiss()
    }
}




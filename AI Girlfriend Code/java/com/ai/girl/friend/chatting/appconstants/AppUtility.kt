package com.ai.girl.friend.chatting.appconstants

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.ai.girl.friend.chatting.appDialogs.ProgressDialog

var progressdialog: ProgressDialog? = null
fun ShowProgress(message: String, isTittleShow: Boolean, activity: Activity) {
    progressdialog = ProgressDialog(activity, message, isTittleShow)
    progressdialog!!.show()
}

fun dismissProgress() {
    if (progressdialog!!.isShowing) {
        progressdialog!!.dismiss()
    }
}


fun View.gone() {
    this.visibility = View.GONE
}

fun View.visiable() {
    this.visibility = View.VISIBLE
}
fun RecyclerView.initRecyclerView(layoutManager: RecyclerView.LayoutManager,adapter:Adapter<*>)
{
    this.layoutManager = layoutManager
    this.adapter=adapter
}
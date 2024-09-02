package com.ai.girl.friend.chatting.appconstants

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.ai.girl.friend.chatting.interfaces.KeyboardHeightListener

class KeyboardHeightProvider(
    context: Context,
    windowManager: WindowManager,
    view: View,
    keyboardHeightListener: KeyboardHeightListener?
) : PopupWindow(context) {


    init {
        val linearLayout = LinearLayout(context)
        linearLayout.layoutParams = LinearLayout.LayoutParams(-1, -1)
        linearLayout.viewTreeObserver.addOnGlobalLayoutListener {
            settingDisplay(
                windowManager,
                linearLayout,
                context,
                keyboardHeightListener
            )
        }
        contentView = linearLayout
        softInputMode = LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED
        inputMethodMode = 1
        width = 0
        height = -1
        setBackgroundDrawable(ColorDrawable(0))
        view.post { showAtLocation(view, 0, 0, 0) }
    }





        fun settingDisplay(
            windowManager: WindowManager,
            linearLayout: LinearLayout,
            context: Context,
            keyboardHeightListener: KeyboardHeightListener?
        ) {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            val rect = Rect()
            linearLayout.getWindowVisibleDisplayFrame(rect)
            var i = displayMetrics.heightPixels - (rect.bottom - rect.top)
            val identifier =
                context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (identifier > 0) {
                i -= context.resources.getDimensionPixelSize(identifier)
            }
            if (i < 100) {
                i = 0
            }
            val z = displayMetrics.widthPixels > displayMetrics.heightPixels
            val z2 = i > 0
            keyboardHeightListener?.onKeyboardHeightChanged(i, z2, z)
        }

}

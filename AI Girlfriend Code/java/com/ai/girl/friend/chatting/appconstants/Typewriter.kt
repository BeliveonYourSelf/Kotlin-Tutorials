package com.ai.girl.friend.chatting.appconstants

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class Typewriter : AppCompatTextView {
    private var characterAdder: Runnable? = null


    private var mDelay: Long
    private var mHandler: Handler
    private var mIndex = 0
    private var mText: CharSequence? = null

        fun settingTextview(typewriter: Typewriter): Int {
            val i = typewriter.mIndex
            typewriter.mIndex = i + 1
            return i
        }

    constructor(context: Context?) : super(context!!) {
        this.mDelay = 500L
        this.mHandler = Handler()
        this.characterAdder = Runnable {
            val typewriter = this@Typewriter
            typewriter.text = typewriter.mText!!.subSequence(
                0, settingTextview(
                    this@Typewriter
                )
            )
            if (this@Typewriter.mIndex <= mText!!.length) {
                mHandler.postDelayed(this@Typewriter.characterAdder!!, this@Typewriter.mDelay)
            }
        }
    }

    constructor(context: Context?, attributeSet: AttributeSet?) : super(
        context!!, attributeSet
    ) {
        this.mDelay = 500L
        this.mHandler = Handler()
        this.characterAdder = Runnable {
            val typewriter = this@Typewriter
            typewriter.text = typewriter.mText!!.subSequence(
                0,settingTextview(
                    this@Typewriter
                )
            )
            if (this@Typewriter.mIndex <= mText!!.length) {
                mHandler.postDelayed(characterAdder!!, mDelay)
            }
        }
    }

    fun animateText(charSequence: CharSequence?) {
        this.mText = charSequence
        this.mIndex = 0
        text = ""
        mHandler.removeCallbacks(characterAdder!!)
        mHandler.postDelayed(characterAdder!!, this.mDelay)
    }

    fun setCharacterDelay(j: Long) {
        this.mDelay = j
    }


}

package com.ai.girl.friend.chatting.adapters

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ai.girl.friend.chatting.appDialogs.DialogReport
import com.ai.girl.friend.chatting.appconstants.gone
import com.ai.girl.friend.chatting.appconstants.visiable
import com.ai.girl.friend.chatting.databinding.ItemMessageBinding
import com.ai.girl.friend.chatting.datamodels.NewChatModel

class ChatAdapters(val activity: Activity, val arrayList: List<NewChatModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var giftLike: Boolean = true
    var giftDislike: Boolean = true
    lateinit var dialogReport: DialogReport

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            ItemMessageBinding.inflate(
                LayoutInflater.from(activity),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = arrayList.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder = holder as MyViewHolder
        with(myViewHolder.binding) {
            val model = arrayList[position]
            if (arrayList[position].senderType.equals("ai")) {
                if (position == arrayList.size - 1) {
                    mLoading.visiable()
                    mAnsView.gone()
                    if (arrayList[position].isFromHistory.equals("true")) {
                        mLoading.gone()
                        mAnsView.visiable()
                        contentLikeReport.visiable()
                        tvMsg1.setCharacterDelay(25L)
                        tvMsg1.animateText(arrayList[position].ansText)
                    } else {
                        Handler(Looper.getMainLooper()).postDelayed({
                            mLoading.gone()
                            mAnsView.visiable()
                            contentLikeReport.visiable()
                            tvMsg1.setCharacterDelay(25L)
                            tvMsg1.animateText(arrayList[position].ansText)
                        }, 1500L)
                    }

                    layoutLeft.visiable()
                    layoutLeft.gone()
                } else {
                    contentLikeReport.visiable()
                    tvMsg1.text = arrayList[position].ansText
                }
                layoutLeft.visiable()
                layoutRight.gone()
            } else {
                layoutRight.visiable()
                layoutLeft.gone()
                if (arrayList[position].questionType.equals("image")) {
                    tvMsg2.gone()
                    giftSticker.visiable()
                    giftSticker.setImageResource(arrayList[position].questionImage)

                } else {
                    tvMsg2.text = arrayList[position].questionText
                }
            }

            mLike.setOnClickListener {
                if (giftLike) {
                    mDislike.gone()
                    giftLike = false
                    return@setOnClickListener
                }
                mDislike.visiable()
                giftLike = true
            }
            mDislike.setOnClickListener {
                if (giftLike) {
                    mDislike.gone()
                    giftLike = false
                    return@setOnClickListener
                }
                mLike.visiable()
                giftDislike = true
            }
            mReport.setOnClickListener {
                    dialogReport  = DialogReport(activity)
                    dialogReport.show()
            }
        }
    }

    class MyViewHolder(val binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root)
}
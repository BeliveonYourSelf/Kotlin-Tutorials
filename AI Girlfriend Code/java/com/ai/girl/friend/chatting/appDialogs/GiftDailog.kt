package com.ai.girl.friend.chatting.appDialogs

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ai.girl.friend.chatting.R
import com.ai.girl.friend.chatting.adapters.GiftAdapter
import com.ai.girl.friend.chatting.appconstants.initRecyclerView
import com.ai.girl.friend.chatting.databinding.DialogGiftBinding
import com.ai.girl.friend.chatting.datamodels.GiftModel
import com.ai.girl.friend.chatting.interfaces.onItemSelect
import com.google.android.material.bottomsheet.BottomSheetDialog

class GiftDailog(val activity: Activity, val onItemSelect: onItemSelect) :
    BottomSheetDialog(activity,R.style.AppBottomSheetDialogTheme) {
    val binding = DialogGiftBinding.inflate(layoutInflater)
    lateinit var giftAdapter: GiftAdapter
    var giftArraylist: MutableList<GiftModel> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setCancelable(true)

        giftArraylist = mutableListOf(
            GiftModel(R.drawable.ice_cream, "Ice Cream", "Free"),
            GiftModel(R.drawable.lipstick, "LipStick", "Free"),
            GiftModel(R.drawable.lily, "Lily", "Free"),
            GiftModel(R.drawable.love, "Love Sticker", "Free")
        )
        giftAdapter = GiftAdapter(activity, giftArraylist, object : onItemSelect {
            override fun onItemClick(pos: Int) {
                onItemSelect.onItemClick(pos)
                dismiss()
            }
        })
        binding.recyclerGift.initRecyclerView(LinearLayoutManager(activity), giftAdapter)
        binding.mClose.setOnClickListener {
            dismiss()
        }
    }
}
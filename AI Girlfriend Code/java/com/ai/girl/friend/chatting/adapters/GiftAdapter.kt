package com.ai.girl.friend.chatting.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ai.girl.friend.chatting.databinding.ItemGiftBinding
import com.ai.girl.friend.chatting.datamodels.GiftModel
import com.ai.girl.friend.chatting.interfaces.onItemSelect

class GiftAdapter(
    val activity: Activity,
    val arrayList: List<GiftModel>,
    val onItemSelect: onItemSelect
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            ItemGiftBinding.inflate(
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
            giftImage.setImageResource(arrayList[position].gift)
            name.text = arrayList[position].name
            price.text = arrayList[position].price
            root.setOnClickListener {
                onItemSelect.onItemClick(position)
            }
        }

    }

    class MyViewHolder(val binding: ItemGiftBinding) : RecyclerView.ViewHolder(binding.root)
}
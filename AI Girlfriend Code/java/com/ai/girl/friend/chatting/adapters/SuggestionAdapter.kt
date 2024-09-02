package com.ai.girl.friend.chatting.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ai.girl.friend.chatting.databinding.ItemGiftBinding
import com.ai.girl.friend.chatting.databinding.ItemSuggestionBinding
import com.ai.girl.friend.chatting.interfaces.onItemSelect

class SuggestionAdapter(
    val activity: Activity,
    val arrayList: List<String>,
    val onItemSelect: onItemSelect
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemSuggestionBinding.inflate(LayoutInflater.from(activity), parent, false))
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val myViewHolder = holder as MyViewHolder
        with(myViewHolder.binding) {
            tvSuggestion.text=arrayList[position]
            root.setOnClickListener {
                onItemSelect.onItemClick(position)
            }
        }
    }

    class MyViewHolder(val binding: ItemSuggestionBinding) : RecyclerView.ViewHolder(binding.root)
}
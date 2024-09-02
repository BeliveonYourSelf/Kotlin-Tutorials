package com.ai.girl.friend.chatting.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ai.girl.friend.chatting.R
import com.ai.girl.friend.chatting.databinding.ItemPersonalInterestBinding
import com.ai.girl.friend.chatting.datamodels.PersonalInterestModel
import com.ai.girl.friend.chatting.interfaces.onItemSelect
import com.bumptech.glide.Glide

class PersonalInterestAdapter(
    val activity: Activity,
    val arrayList: List<PersonalInterestModel>,
    onItemSelect: onItemSelect
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var iSelectedPos = 0;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            ItemPersonalInterestBinding.inflate(
                LayoutInflater.from(activity),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder = holder as MyViewHolder

        Glide.with(activity)
            .load(arrayList[position].ivLogo)
            .into(myViewHolder.binding.imageView)
        myViewHolder.binding.tvTittle.text=arrayList[position].vTittle
        with(myViewHolder.binding) {
            if (iSelectedPos == position) {
                conMain.setBackgroundResource(R.drawable.bg_border_filled)
                tvTittle.setTextColor(ContextCompat.getColor(activity, R.color.black))
            } else {
                conMain.setBackgroundResource(R.drawable.bg_border)
                tvTittle.setTextColor(ContextCompat.getColor(activity, R.color.white))
            }
        }

        with(myViewHolder.binding) {
            root.setOnClickListener {
                iSelectedPos = position
                notifyDataSetChanged()
            }
        }

    }

    class MyViewHolder(val binding: ItemPersonalInterestBinding) :
        RecyclerView.ViewHolder(binding.root)
}
package com.ai.girl.friend.chatting.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ai.girl.friend.chatting.R
import com.ai.girl.friend.chatting.activity.comman.SelectAiGirlActivity
import com.ai.girl.friend.chatting.appprefrence.SharedPreferenceUtils
import com.ai.girl.friend.chatting.databinding.ItemImagelistBinding
import com.ai.girl.friend.chatting.interfaces.onItemSelect
import com.bumptech.glide.Glide

class ImageAdapters(
    val activity: Activity,
    val imagearray: IntArray,
    val onItemSelect: onItemSelect
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var iSelected = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            ItemImagelistBinding.inflate(
                LayoutInflater.from(activity),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return imagearray.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder = holder as MyViewHolder
        iSelected = SharedPreferenceUtils.selectedImagePosition
        Glide.with(activity)
            .load(imagearray[position])
            .into(myViewHolder.binding.imgView)

        if (iSelected == position) {
            myViewHolder.binding.imgBorder.setBackgroundResource(R.drawable.rent_circle)
            (activity as SelectAiGirlActivity).setSelectedImage(position)
            return
        }
        myViewHolder.binding.imgBorder.setBackgroundResource(R.drawable.rent_circle_no_value)
        myViewHolder.binding.root.setOnClickListener {
            if (SharedPreferenceUtils.pronounsToSelectCharacter) {
                (activity as SelectAiGirlActivity).setSelectedImage(position)
                SharedPreferenceUtils.selectedImagePosition = position
                onItemSelect.onItemClick(position)
                notifyDataSetChanged()
            }else{

            }

        }

    }

    class MyViewHolder(var binding: ItemImagelistBinding) : RecyclerView.ViewHolder(binding.root)
}
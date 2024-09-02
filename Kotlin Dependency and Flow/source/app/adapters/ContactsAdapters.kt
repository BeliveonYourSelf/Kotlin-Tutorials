package com.learn.app.kotlins.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.learn.app.kotlins.database.ContactEntity
import com.learn.app.kotlins.databinding.ItemContactsBinding
import javax.inject.Inject

class ContactsAdapters @Inject constructor() : RecyclerView.Adapter<ContactsAdapters.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemContactsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.setData(differ.currentList[position])
    }

    inner class ViewHolder(private val binding: ItemContactsBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun setData(items:ContactEntity)
            {
                with(binding) {
                    tvName.text=items.name
                    tvPhone.text=items.phone
                }
            }

    }

    private val differCallBack = object : DiffUtil.ItemCallback<ContactEntity>() {
        override fun areItemsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallBack)

}
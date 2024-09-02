package com.learn.app.kotlins.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.learn.app.kotlins.R
import com.learn.app.kotlins.databinding.ItemMovieBinding
import com.learn.app.kotlins.datamodels.Movy
import javax.inject.Inject

class MovieAdapters @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myHolder = holder as MyViewHolder
        Log.e("TAG", "onBindViewHolder: ----> ", )
        myHolder.bindData(differ.currentList[position])
        myHolder.setIsRecyclable(false)
    }

    inner class MyViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(items: Movy) {
            with(binding) {
                imageView.load(items.image) {
                    crossfade(true)
                    crossfade(500)
                    placeholder(R.drawable.ic_dollar)
                    error(R.drawable.ic_dollar)
                    tvMovieName.text = items.title
                    tvTimeLine.text = items.timeline
                    tvYear.text = items.year
                    tvRating.text = items.imdbRating
                }
            }
        }

    }

    private val differCallBack = object : DiffUtil.ItemCallback<Movy>() {
        override fun areItemsTheSame(oldItem: Movy, newItem: Movy): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movy, newItem: Movy): Boolean {
            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, differCallBack)
}

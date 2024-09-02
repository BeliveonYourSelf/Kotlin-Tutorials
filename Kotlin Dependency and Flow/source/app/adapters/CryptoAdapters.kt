package com.learn.app.kotlins.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.learn.app.kotlins.R
import com.learn.app.kotlins.apputills.Constants
import com.learn.app.kotlins.apputills.roundToThreeDecimals
import com.learn.app.kotlins.databinding.ItemBinding
import com.learn.app.kotlins.datamodels.CoinResponse
import javax.inject.Inject


class CryptoAdapters @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder = holder as MyViewHolder
        myViewHolder.bind(item = differ.currentList[position])
        myViewHolder.setIsRecyclable(false)

    }

    inner class MyViewHolder(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CoinResponse.CoinResponseItem) {
            with(binding) {
                tvName.text = item.id
                tvSymbol.text = item.symbol?.uppercase()
                tvPrice.text = "Rs.${item.current_price?.roundToThreeDecimals()}"
                imgCrypto.load(item.image) {
                    crossfade(true)
                    crossfade(500)
                    placeholder(R.drawable.ic_dollar)
                    error(R.drawable.ic_dollar)
                }

                lineChart.gradientFillColors =
                    intArrayOf(
                        Color.parseColor("#2a9085"),
                        Color.TRANSPARENT
                    )

                lineChart.animation.duration = Constants.animationDuration

                root.setOnClickListener {
                    onItemClick?.invoke(differ.currentList[position])
                }
            }
        }
    }

    private var onItemClick: ((CoinResponse.CoinResponseItem) -> Unit)? = null
    fun setClickListener(listener: (CoinResponse.CoinResponseItem) -> Unit) {
        onItemClick = listener
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<CoinResponse.CoinResponseItem>(){
        override fun areItemsTheSame(oldItem: CoinResponse.CoinResponseItem, newItem: CoinResponse.CoinResponseItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CoinResponse.CoinResponseItem, newItem: CoinResponse.CoinResponseItem): Boolean {
            return oldItem == newItem
        }

    }


    val differ = AsyncListDiffer(this, diffCallBack)


}
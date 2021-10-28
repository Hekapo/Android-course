package com.example.firstlesson.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.firstlesson.R
import com.example.firstlesson.databinding.OneWaifuBinding
import com.example.firstlesson.model.Waifu

class WaifuAdapter(private val list: List<Waifu>, glide: RequestManager) :
    RecyclerView.Adapter<WaifuAdapter.WaifuViewHolder>() {
    private var glideInst: RequestManager? = null

    init {
        glideInst = glide
    }

    private lateinit var onCLickListener: OnWaifuClickListener

    interface OnWaifuClickListener {
        fun onWaifuClicked(id: Int)

    }

    fun setOnItemClickListener(clickListener: OnWaifuClickListener) {
        onCLickListener = clickListener

    }

    inner class WaifuViewHolder(val binding: OneWaifuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun clickItem(id: Int) {
            binding.root.setOnClickListener {
                onCLickListener.onWaifuClicked(id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaifuViewHolder {
        return WaifuViewHolder(
            OneWaifuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WaifuViewHolder, position: Int) {
        holder.binding.tvName.text = list[position].name
        holder.binding.tvAge.text = list[position].age
        holder.binding.tvAbout.text = list[position].about

        glideInst?.load(list[position].imageUrl)?.diskCacheStrategy(DiskCacheStrategy.DATA)
            ?.placeholder(R.drawable.yamete_kudasai)?.into(holder.binding.ivImage)

        holder.clickItem(list[position].id)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

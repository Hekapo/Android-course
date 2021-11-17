package com.example.firstlesson.adapters.simple_item_list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstlesson.databinding.ViewPagerItemBinding

class SimpleItemAdapter(
    private val images: List<Int>
) : RecyclerView.Adapter<SimpleItemAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ViewPagerItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        return ViewPagerViewHolder(
            ViewPagerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentImage = images[position]
        holder.binding.ivImage.setImageResource(currentImage)
    }

    override fun getItemCount(): Int {

        return images.size
    }
}

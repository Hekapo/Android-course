package com.example.firstlesson.adapters.list_fragment_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firstlesson.databinding.OneItemBinding
import com.example.firstlesson.models.News


class ItemAdapter : ListAdapter<News, ItemAdapter.NewsWrapperHolder>(object :
    DiffUtil.ItemCallback<News>() {

    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.description == newItem.description && oldItem.title == newItem.title
    }

}) {
    private lateinit var itemDeleteListener: ItemDeleteListener

    interface ItemDeleteListener {
        fun delete(news: News)
    }

    fun setOnItemDeleteListener(clickListener: ItemDeleteListener) {
        itemDeleteListener = clickListener

    }

    inner class NewsWrapperHolder(var binding: OneItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun deleteItem(news: News) {
            binding.deleteIv.setOnClickListener {
                itemDeleteListener.delete(news)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsWrapperHolder {
        return NewsWrapperHolder(
            OneItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsWrapperHolder, position: Int) {
        holder.binding.description.text = getItem(position).description
        holder.binding.title.text = getItem(position).title
        holder.deleteItem(getItem(position))
    }

}

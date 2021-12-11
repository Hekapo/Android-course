package com.example.firstlesson.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstlesson.databinding.OneMusicItemBinding
import com.example.firstlesson.models.Song

class SongAdapter(private val list: List<Song>) :
    RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    interface OnSongClickListener {
        fun setOnclick(song: Song)
    }

    lateinit var onSongClickListener: OnSongClickListener

    fun setOnItemClickListener(
        onSongClickListener: OnSongClickListener
    ) {
        this.onSongClickListener = onSongClickListener

    }

    inner class SongViewHolder(val binding: OneMusicItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun clickSong(song: Song) {
            binding.root.setOnClickListener {
                onSongClickListener.setOnclick(song)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            OneMusicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        with(holder) {
            binding.songImage.setImageResource(list[position].cover)
            binding.songTitleTw.text = list[position].audioTitle
            clickSong(list[position])
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

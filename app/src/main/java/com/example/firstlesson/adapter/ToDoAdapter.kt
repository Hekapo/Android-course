package com.example.firstlesson.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ToDoItem
import com.example.firstlesson.databinding.TodoItemBinding

class ToDoAdapter(context: Context, onToDoClicked: OnToDoClicked, onToDoDelete: OnToDoDelete) :
    ListAdapter<ToDoItem, ToDoAdapter.ToDoHolder>(object : DiffUtil.ItemCallback<ToDoItem>() {
        override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
            return oldItem.description == newItem.description && oldItem.title == newItem.title
        }

    }) {

    private var onToDoClicked: OnToDoClicked? = null
    private var onToDoDelete: OnToDoDelete? = null
    private var layoutInflater: LayoutInflater? = null

    init {
        this.onToDoClicked = onToDoClicked
        this.onToDoDelete = onToDoDelete
        this.layoutInflater = LayoutInflater.from(context)

    }

    interface OnToDoClicked {
        fun onClick(toDoItem: ToDoItem)
    }

    interface OnToDoDelete {
        fun onDelete(toDoItem: ToDoItem)
    }

    inner class ToDoHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            with(binding.root) {
                setOnClickListener {
                    val item = getItem(layoutPosition)
                    onToDoClicked?.onClick(item)
                }
            }
            with(binding.deleteIv) {
                setOnClickListener {
                    val item = getItem(layoutPosition)
                    onToDoDelete?.onDelete(item)

                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.ToDoHolder {
        return ToDoHolder(
            TodoItemBinding.inflate(
                checkNotNull(layoutInflater),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ToDoAdapter.ToDoHolder, position: Int) {
        with(holder) {
            binding.titleTv.text = getItem(position).title
            binding.descTv.text = getItem(position).description

        }
    }
}

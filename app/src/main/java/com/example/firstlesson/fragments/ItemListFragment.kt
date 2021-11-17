package com.example.firstlesson.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.firstlesson.AddNewsDialog
import com.example.firstlesson.adapters.list_fragment_adapter.ItemAdapter
import com.example.firstlesson.adapters.list_fragment_adapter.SwipeToDelete
import com.example.firstlesson.databinding.FragmentItemListBinding
import com.example.firstlesson.models.News
import com.example.firstlesson.presenters.ListViewPresenter
import com.example.firstlesson.views.list_view.ListView

class ItemListFragment : Fragment(), ListView, ItemAdapter.ItemDeleteListener {

    private lateinit var binding: FragmentItemListBinding
    private val presenter by lazy {
        ListViewPresenter(this)
    }
    private val adapter = ItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)


        setupRecyclerView()
        presenter.getNews()

        binding.addItem.setOnClickListener {
            showDialog()

        }


        return binding.root
    }

    override fun setData(list: List<News>) {
        adapter.submitList(list)
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        adapter.setOnItemDeleteListener(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        swipeToDelete(recyclerView)
    }

    override fun showDialog() {
        AddNewsDialog.show(childFragmentManager, presenter)

    }

    override fun delete(news: News) {
        presenter.delete(news)
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemToDelete = adapter.currentList[viewHolder.adapterPosition]
                presenter.delete(itemToDelete)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


}

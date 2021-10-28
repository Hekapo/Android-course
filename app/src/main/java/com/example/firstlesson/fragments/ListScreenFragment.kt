package com.example.firstlesson.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.firstlesson.R
import com.example.firstlesson.adapters.WaifuAdapter
import com.example.firstlesson.controller.list_screen.ListScreenPresenter
import com.example.firstlesson.controller.list_screen.ListScreenView
import com.example.firstlesson.databinding.FragmentListScreenBinding
import com.example.firstlesson.model.Waifu

class ListScreenFragment : Fragment(), WaifuAdapter.OnWaifuClickListener, ListScreenView {

    private lateinit var binding: FragmentListScreenBinding
    private lateinit var adapter: WaifuAdapter
    private var listOfWaifu = emptyList<Waifu>()
    private lateinit var presenter: ListScreenPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.custom_toolbar)
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        toolbar.setupWithNavController(navController, appBarConfiguration)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListScreenBinding.inflate(inflater, container, false)
        presenter = ListScreenPresenter(this)
        presenter.getAllWaifus()


        setupRecyclerView(listOfWaifu)

        return binding.root

    }

    private fun setupRecyclerView(list: List<Waifu>) {
        adapter = WaifuAdapter(list, Glide.with(this))
        binding.rvWaifu.adapter = adapter
        adapter.setOnItemClickListener(this)


    }

    override fun onWaifuClicked(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        presenter.navigateTo(R.id.action_homeScreenFragment_to_detailsScreenFragment, bundle)


    }

    override fun navigate(id: Int, arg: Bundle?) {
        binding.root.findNavController().navigate(id, arg)
    }

    override fun setWaifus(list: List<Waifu>) {
        listOfWaifu = list
    }
}

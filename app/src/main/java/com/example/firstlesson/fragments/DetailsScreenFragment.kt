package com.example.firstlesson.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.firstlesson.LoadImage
import com.example.firstlesson.R
import com.example.firstlesson.controller.details_screen.DetailsScreenPresenter
import com.example.firstlesson.controller.details_screen.DetailsScreenView
import com.example.firstlesson.databinding.FragmentDetailsScreenBinding
import com.example.firstlesson.model.Waifu
import com.google.android.material.appbar.CollapsingToolbarLayout


class DetailsScreenFragment : Fragment(), DetailsScreenView {

    private lateinit var binding: FragmentDetailsScreenBinding
    private lateinit var presenter: DetailsScreenPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsScreenBinding.inflate(inflater, container, false)
        presenter = DetailsScreenPresenter(this)

        val waifu = arguments?.getInt("id")

        if (waifu != null) {
            presenter.getWaifu(waifu)
        }

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    presenter.navigateTo(R.id.action_detailsScreenFragment_to_homeScreenFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root

    }

    override fun setWaifu(waifu: Waifu) {
        binding.tvDetAge.text = waifu.age
        binding.tvDetAbout.text = waifu.about
        binding.tvDetName.text = waifu.name
        LoadImage.load(waifu.imageUrl, binding.ivDetScreen)
    }

    override fun navigate(destination: Int) {
        binding.root.findNavController().navigate(destination)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()

        val layout = view.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar_layout)
        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        layout.setupWithNavController(toolbar, navController, appBarConfiguration)

        super.onViewCreated(view, savedInstanceState)


    }

}

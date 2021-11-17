package com.example.firstlesson.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firstlesson.R
import com.example.firstlesson.adapters.simple_item_list_adapter.SimpleItemAdapter
import com.example.firstlesson.databinding.FragmentSimpleItemListBinding

class SimpleItemListFragment : Fragment() {

    private lateinit var binding: FragmentSimpleItemListBinding

    private lateinit var adapter: SimpleItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSimpleItemListBinding.inflate(inflater, container, false)

        val listOfImages = listOf(
            R.drawable.cat4,
            R.drawable.cat,
            R.drawable.cat2,
            R.drawable.sad_cat,
            R.drawable.billy,
        )

        adapter = SimpleItemAdapter(listOfImages)
        binding.viewpager.adapter = adapter


        return binding.root
    }

}

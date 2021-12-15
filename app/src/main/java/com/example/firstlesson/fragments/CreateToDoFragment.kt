package com.example.firstlesson.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.domain.model.ToDoItem
import com.example.firstlesson.databinding.FragmentCreateToDoBinding
import com.example.firstlesson.presenter.CreateToDoPresenter
import com.example.firstlesson.view.CreateToDoView
import java.util.*

class CreateToDoFragment : Fragment(), CreateToDoView {

    private val binding: FragmentCreateToDoBinding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentCreateToDoBinding.inflate(layoutInflater)
    }

    private val presenter by lazy {
        CreateToDoPresenter(this, requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar = Calendar.getInstance()

        val args = arguments?.getLong("ID")
        presenter.getOneToDo(args)

        binding.createBtn.setOnClickListener {
            val title = binding.titleEt.text.toString()
            val desc = binding.descEt.text.toString()
            val date = calendar.time

            presenter.createToDo(title, desc, date)

        }

        binding.updateBtn.setOnClickListener {
            val title = binding.titleEt.text.toString()
            val desc = binding.descEt.text.toString()
            val date = calendar.time
            presenter.updateToDo(title, desc, args, date)

        }

    }

    override fun navigateBack() {
        binding.root.findNavController().popBackStack()
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }

    override fun showButton() {
        binding.createBtn.visibility = View.VISIBLE
    }

    override fun setInitialText(todoItem: ToDoItem) {
        binding.descEt.setText(todoItem.description)
        binding.titleEt.setText(todoItem.title)
    }

}

package com.example.firstlesson.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.data.room.DatabaseProvider
import com.example.data.room.ToDoDatabase
import com.example.data.room.dao.ToDoDAO
import com.example.domain.model.ToDoItem
import com.example.firstlesson.R
import com.example.firstlesson.adapter.ToDoAdapter
import com.example.firstlesson.databinding.FragmentHomeScreenBinding
import com.example.firstlesson.presenter.ToDoListPresenter
import com.example.firstlesson.utils.hideKeyboard
import com.example.firstlesson.view.ToDoListView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

class HomeScreenFragment : Fragment(), ToDoAdapter.OnToDoClicked, ToDoListView,
    ToDoAdapter.OnToDoDelete, DatabaseProvider {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val todoAdapter by lazy {
        ToDoAdapter(requireContext(), this, this)
    }

    private val presenter by lazy {
        ToDoListPresenter(this, this, scope = scope)
    }

    private val binding: FragmentHomeScreenBinding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentHomeScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)

        requireActivity().hideKeyboard()

        setupRecyclerview()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.clearStorage()

        presenter.loadToDo()

        binding.addBtn.setOnClickListener {
            presenter.navigate(R.id.action_homeScreenFragment_to_createToDoFragment, null)
        }

    }

    private fun setupRecyclerview() {
        binding.todoRv.apply {
            adapter = todoAdapter
        }
    }

    override fun onClickToDo(toDoItem: ToDoItem) {
        presenter.navigate(R.id.action_homeScreenFragment_to_createToDoFragment, toDoItem)

    }

    override fun showAllToDo(toDoItem: List<ToDoItem>) {
        renderData(toDoItem)
    }

    override fun navigateTo(destination: Int) {
        binding.root.findNavController().navigate(destination)
    }

    override fun showEmptyData() {
        binding.emptyDbTv.visibility = View.VISIBLE
    }

    override fun hideEmptyData() {
        binding.emptyDbTv.visibility = View.GONE
    }

    private fun renderData(list: List<ToDoItem>) {
        todoAdapter.submitList(list)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item, menu)
        val deleteAll = menu.findItem(R.id.delete_all)
        val deleteAllView = deleteAll.actionView as? SearchView

        deleteAllView?.isSubmitButtonEnabled = true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all -> presenter.deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDelete(toDoItem: ToDoItem) {
        presenter.delete(toDoItem)
    }

    override fun provideDataBase(): ToDoDatabase {
        return ToDoDatabase.invoke(requireContext())
    }

    override fun provideDao(): ToDoDAO {
        return provideDataBase().toDoDAO()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scope.cancel()
    }

}

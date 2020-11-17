package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.adapters.TodoViewAdapter
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.TodoViewModel

class TodoListFragment : Fragment() {

    private lateinit var mTodoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo_list, container, false)
        val btnAdd = view.findViewById<FloatingActionButton>(R.id.btnAdd)

        val recyclerView: RecyclerView = view.findViewById(R.id.todo_list_recycler_view)
        val adapter = TodoViewAdapter()

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        mTodoViewModel.readAllTodos.observe(viewLifecycleOwner, { todos ->
            adapter.setData(todos)
        })

        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_todoListFragment_to_addTodoFragment)
        }
        return view
    }
}
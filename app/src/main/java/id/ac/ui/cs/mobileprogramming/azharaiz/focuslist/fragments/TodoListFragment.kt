package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.adapters.TodoViewAdapter
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Todo
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.TodoViewModel

class TodoListFragment : Fragment(), TodoStatusListener {

    private val TAG = "TODO_LIST_FRAGMENT"

    private val mTodoViewModel: TodoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo_list, container, false)
        val btnAdd = view.findViewById<FloatingActionButton>(R.id.btnAdd)

        val recyclerView: RecyclerView = view.findViewById(R.id.todo_list_recycler_view)
        val adapter = TodoViewAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        mTodoViewModel.readAllTodos.observe(viewLifecycleOwner, { todos ->
            adapter.setData(todos)
        })

        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_todoListFragment_to_addTodoFragment)
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "START")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "DESTROY")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "STOP")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "RESUME")
    }

    override fun onTodoStatusUpdate(todo: Todo) {
        mTodoViewModel.updateTodo(todo)
    }

    override fun onTodoClicked(todo: Todo) {
        mTodoViewModel.updateData(todo)
    }

    override fun checkOrientation(): Int {
        return requireActivity().resources.configuration.orientation
    }
}

interface TodoStatusListener {
    fun onTodoStatusUpdate(todo: Todo)
    fun onTodoClicked(todo: Todo)
    fun checkOrientation(): Int
}
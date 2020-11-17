package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Todo
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.TodoViewModel
import kotlinx.android.synthetic.main.fragment_add_todo.view.*

class AddTodoFragment : Fragment() {

    private lateinit var mTodoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_todo, container, false)
        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        view.findViewById<Button>(R.id.addTodoBtn).setOnClickListener {
            insertDataToDatabase(view)
            (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(view.windowToken, 0)
        }

        view.addTodoTitle.requestFocus()

        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

        return view
    }

    private fun insertDataToDatabase(view: View) {
        val todoTitle = view.findViewById<EditText>(R.id.addTodoTitle).text.toString()
        val todoStatus = view.findViewById<CheckBox>(R.id.addTodoCheckbox).isChecked
        if (!TextUtils.isEmpty(todoTitle)) {
            val todo = Todo(0, todoTitle, todoStatus)
            mTodoViewModel.addTodo(todo)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addTodoFragment_to_todoListFragment)
        }
    }
}
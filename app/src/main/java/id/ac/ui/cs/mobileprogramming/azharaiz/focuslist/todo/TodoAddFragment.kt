package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.todo

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Todo

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
        }

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
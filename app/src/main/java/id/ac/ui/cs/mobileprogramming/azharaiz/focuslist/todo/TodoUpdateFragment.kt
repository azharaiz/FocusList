package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.todo

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Todo
import kotlinx.android.synthetic.main.fragment_todo_update.*
import kotlinx.android.synthetic.main.fragment_todo_update.view.*

class TodoUpdateFragment : Fragment() {

    private val args by navArgs<TodoUpdateFragmentArgs>()

    private lateinit var mTodoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo_update, container, false)

        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)


        view.updateTodoTitle.setText(args.currentTodo.title)
        view.updateTodoCheckbox.isChecked = args.currentTodo.status

        view.updateTodoBtn.setOnClickListener {
            updateItem()
        }

        view.deleteTodoBtn.setOnClickListener {
            deleteItem(args.currentTodo)
        }

        return view
    }

    private fun updateItem() {
        val title = updateTodoTitle.text
        val status = updateTodoCheckbox.isChecked

        if (!TextUtils.isEmpty(title)) {
            val updatedTodo = Todo(args.currentTodo.id, title.toString(), status)

            mTodoViewModel.updateTodo(updatedTodo)

            Toast.makeText(requireContext(), "Update success", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_todoUpdateFragment_to_todoListFragment)
        }
    }

    private fun deleteItem(todo: Todo) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTodoViewModel.deleteTodo(todo)
            findNavController().navigate(R.id.action_todoUpdateFragment_to_todoListFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentTodo.title}?")
        builder.setTitle("Are you sure you want to delete ${args.currentTodo.title}?")
        builder.create().show()
    }
}
package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Todo
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments.TodoListFragmentDirections
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments.TodoStatusListener
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.TodoViewModel
import kotlinx.android.synthetic.main.fragment_todo_item.view.*

class TodoViewAdapter(private val onTodoStatusListener: TodoStatusListener) :
    RecyclerView.Adapter<TodoViewAdapter.TodoViewHolder>() {

    private var todoList = emptyList<Todo>()
    private lateinit var todoViewModel: TodoViewModel

    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.todoItemText)
        val checkBox: CheckBox = view.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_todo_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentItem = todoList[position]
        holder.textView.text = currentItem.title
        holder.checkBox.isChecked = currentItem.status

        holder.itemView.findViewById<LinearLayout>(R.id.todo_item).setOnClickListener {
            val action =
                TodoListFragmentDirections.actionTodoListFragmentToTodoUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.checkBox.setOnClickListener {
            val clickedTodo = Todo(currentItem.id, currentItem.title, !currentItem.status)
            onTodoStatusListener.onTodoStatusUpdate(clickedTodo)
        }
    }

    override fun getItemCount() = todoList.size

    fun setData(todos: List<Todo>) {
        this.todoList = todos
        notifyDataSetChanged()
    }
}
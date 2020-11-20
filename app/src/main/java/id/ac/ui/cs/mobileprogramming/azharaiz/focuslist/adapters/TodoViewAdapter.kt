package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.adapters

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Todo
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentTodoItemBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments.TodoListFragmentDirections
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments.TodoStatusListener


class TodoViewAdapter(private val onTodoStatusListener: TodoStatusListener) :
    RecyclerView.Adapter<TodoViewAdapter.TodoViewHolder>() {

    private val TAG = "TODO_ADAPTER"

    private var todoList = emptyList<Todo>()

    class TodoViewHolder(
        fragmentTodoItemBinding: FragmentTodoItemBinding,
        val todoStatusListener: TodoStatusListener
    ) : RecyclerView.ViewHolder(fragmentTodoItemBinding.root) {
        private var binding: FragmentTodoItemBinding = fragmentTodoItemBinding

        fun bind(todo: Todo) {
            binding.todo = todo
            binding.executePendingBindings()
        }

        fun navigation(todo: Todo) {
            itemView.setOnClickListener {
                todoStatusListener.onTodoClicked(todo)
                if (todoStatusListener.checkOrientation() == Configuration.ORIENTATION_PORTRAIT) {
                    val action = TodoListFragmentDirections
                        .actionTodoListFragmentToTodoDetailFragment2()
                    itemView.findNavController().navigate(action)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
        val itemBinding =
            FragmentTodoItemBinding.inflate(view, parent, false)
        return TodoViewHolder(itemBinding, onTodoStatusListener)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentItem = todoList[position]
        holder.bind(currentItem)
        holder.navigation(currentItem)
    }

    override fun getItemCount() = todoList.size

    fun setData(todos: List<Todo>) {
        this.todoList = todos
        notifyDataSetChanged()
    }
}
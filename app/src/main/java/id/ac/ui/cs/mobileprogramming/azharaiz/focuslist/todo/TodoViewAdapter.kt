package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R

class TodoViewAdapter(private val dataSet: Array<String>,
                      private val cellClickListener: TodoItemClickListener) :
        RecyclerView.Adapter<TodoViewAdapter.TodoViewHolder>() {

    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.todoItemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_todo_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.textView.text = dataSet[position]

        holder.textView.setOnClickListener {
            cellClickListener.onTodoItemClickListener(dataSet[position])
        }
    }

    override fun getItemCount() = dataSet.size
}
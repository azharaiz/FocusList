package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.TimerLog
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.helpers.TimerHelper

class TimerLogViewAdapter : RecyclerView.Adapter<TimerLogViewAdapter.TimerLogHolder>() {

    private var timerLogList = emptyList<TimerLog>()

    class TimerLogHolder(view: View) : RecyclerView.ViewHolder(view) {
        var timerTodoTitle: TextView = view.findViewById(R.id.timerLogTodoTitle)
        var timerTodoDuration: TextView = view.findViewById(R.id.timerLogDuration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimerLogHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_timer_log_item, parent, false)
        return TimerLogHolder(view)
    }

    override fun onBindViewHolder(holder: TimerLogHolder, position: Int) {
        val currentItem = timerLogList[position]
        holder.timerTodoTitle.text = currentItem.todoTitle
        holder.timerTodoDuration.text = TimerHelper.displayTimer(currentItem.duration)

    }

    override fun getItemCount() = timerLogList.size

    fun setData(timerLog: List<TimerLog>) {
        this.timerLogList = timerLog
        notifyDataSetChanged()
    }
}
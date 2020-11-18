package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.adapters.TimerLogViewAdapter
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.TimerLogViewModel

class TimerLogFragment : Fragment() {

    private lateinit var mTimerLogViewModel: TimerLogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_timer_log, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.timer_log_recycler_view)
        val adapter = TimerLogViewAdapter()

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        mTimerLogViewModel = ViewModelProvider(this).get(TimerLogViewModel::class.java)

        mTimerLogViewModel.readAllTimerLogs.observe(viewLifecycleOwner, { todos ->
            adapter.setData(todos)
        })

        return view
    }

}
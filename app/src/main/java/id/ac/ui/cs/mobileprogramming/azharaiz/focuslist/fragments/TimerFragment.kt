package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.services.TimerService
import kotlinx.android.synthetic.main.fragment_timer.view.*

class TimerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_timer, container, false)

        val intentFilter = IntentFilter()
        intentFilter.addAction("COUNTER")

        val runningReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val timeRemaining = intent?.getIntExtra("TIME_REMAINING", 0)
                view.timerCountDown.text = timeRemaining.toString()
            }
        }
        requireActivity().registerReceiver(runningReceiver, intentFilter)

        val intentStop = IntentFilter()
        intentStop.addAction("STOP")

        val stopReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                view.btnTimerPause.visibility = View.GONE
                view.btnTimerStart.visibility = View.VISIBLE
                view.timerCountDown.text = "25:00"
            }
        }

        requireActivity().registerReceiver(stopReceiver, intentStop)

        view.btnTimerStart.setOnClickListener {
            view.btnTimerStart.visibility = View.GONE
            view.btnTimerPause.visibility = View.VISIBLE
            val intentService = Intent(context, TimerService::class.java)
            intentService.putExtra("TIME_VALUE", 10)
            requireActivity().startService(intentService)
        }

        view.btnTimerStop.setOnClickListener {
            val intentService = Intent(context, TimerService::class.java)
            requireActivity().stopService(intentService)
        }

        return view
    }

}
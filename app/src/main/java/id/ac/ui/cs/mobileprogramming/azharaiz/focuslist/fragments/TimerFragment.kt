package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.helpers.TimerHelper
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.services.TimerService
import kotlinx.android.synthetic.main.fragment_timer.*
import kotlinx.android.synthetic.main.fragment_timer.view.*

class TimerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_timer, container, false)

        registerAllReceiver()

        view.btnTimerStart.setOnClickListener {
            view.btnTimerStart.visibility = View.GONE
            view.btnTimerPause.visibility = View.VISIBLE
            val intentService = Intent(context, TimerService::class.java)
            intentService.putExtra(
                "TIME_VALUE",
                view.timerDurationInput.text.toString().toInt() * 60
            )
            requireActivity().startService(intentService)
        }

        view.btnTimerStop.setOnClickListener {
            val intentService = Intent(context, TimerService::class.java)
            requireActivity().stopService(intentService)
        }

        return view
    }

    private fun registerAllReceiver() {
        registerRunningReceiver()
        registerStopReceiver()
        registerFinishReceiver()
    }

    private fun registerFinishReceiver() {
        val finishedIntent = IntentFilter()
        finishedIntent.addAction("FINISHED")

        val finishedReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Toast.makeText(context, "Timer finished!", Toast.LENGTH_SHORT).show()
                btnTimerPause.visibility = View.GONE
                btnTimerStart.visibility = View.VISIBLE
                timerCountDown.text = intent?.getStringExtra("PREVIOUS_DURATION") ?: "00:00"
            }
        }

        requireActivity().registerReceiver(finishedReceiver, finishedIntent)
    }

    private fun registerStopReceiver() {
        val stopIntent = IntentFilter()
        stopIntent.addAction("STOP")

        val stopReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                btnTimerPause.visibility = View.GONE
                btnTimerStart.visibility = View.VISIBLE
                timerCountDown.text = "25:00"
            }
        }

        requireActivity().registerReceiver(stopReceiver, stopIntent)
    }

    private fun registerRunningReceiver() {
        val startIntent = IntentFilter()
        startIntent.addAction("COUNTER")
        val runningReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val timeRemaining = intent?.getIntExtra("TIME_REMAINING", 0)
                timerCountDown.text = timeRemaining?.let { TimerHelper.displayTimer(it) }
            }
        }
        requireActivity().registerReceiver(runningReceiver, startIntent)
    }

}
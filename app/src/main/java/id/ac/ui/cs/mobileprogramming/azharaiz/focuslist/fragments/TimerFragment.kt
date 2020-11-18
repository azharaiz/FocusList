package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.helpers.TimerHelper
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.services.TimerService
import kotlinx.android.synthetic.main.fragment_timer.*
import kotlinx.android.synthetic.main.fragment_timer.view.*

class TimerFragment : Fragment() {
    private lateinit var mService: TimerService
    private var mBound: Boolean = false

    private lateinit var runningReceiver: BroadcastReceiver

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as TimerService.TimerBinder
            mService = binder.getService()
            mBound = true
            updateUI()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intentService = Intent(context, TimerService::class.java)
        requireActivity().startService(intentService)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_timer, container, false)

        view.btnTimerStart.setOnClickListener {
            view.btnTimerStart.visibility = View.GONE
            view.btnTimerPause.visibility = View.VISIBLE
            view.btnTimerResume.visibility = View.GONE
            view.timerDurationInput.visibility = View.GONE
            if (mBound) {
                mService.setDuration(timerDurationInput.text.toString().toInt())
                mService.startTimer()
            }
        }

        view.btnTimerStop.setOnClickListener {
            if (mBound) {
                mService.stopTimer()
            }
            btnTimerStart.visibility = View.VISIBLE
            btnTimerPause.visibility = View.GONE
            btnTimerResume.visibility = View.GONE
        }

        view.btnTimerPause.setOnClickListener {
            view.btnTimerStart.visibility = View.GONE
            view.btnTimerPause.visibility = View.GONE
            view.btnTimerResume.visibility = View.VISIBLE
            if (mBound) {
                mService.pauseTimer()
            }
        }

        view.btnTimerResume.setOnClickListener {
            view.btnTimerStart.visibility = View.GONE
            view.btnTimerPause.visibility = View.VISIBLE
            view.btnTimerResume.visibility = View.GONE
            if (mBound) {
                mService.resumeTimer()
            }
        }

        view.timerHistoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_timerFragment_to_timerLogFragment)
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        Intent(activity, TimerService::class.java).also { intent ->
            requireActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        val startIntent = IntentFilter()
        startIntent.addAction("COUNTER")
        runningReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val timeRemaining = intent?.getIntExtra("TIME_REMAINING", 0)
                timerCountDown.text = timeRemaining?.let { TimerHelper.displayTimer(it) }
                if (timeRemaining == 0) {
                    btnTimerStart.visibility = View.VISIBLE
                    btnTimerPause.visibility = View.GONE
                    btnTimerResume.visibility = View.GONE
                }
            }
        }

        requireActivity().registerReceiver(runningReceiver, startIntent)
    }

    override fun onStop() {
        super.onStop()
        if (mBound) {
            requireActivity().unbindService(connection)
            mBound = false
        }
        requireActivity().unregisterReceiver(runningReceiver)
    }

    private fun updateUI() {
        if (mBound) {
            when (mService.getTimerStatus()) {
                TimerService.TimerStatus.Paused -> {
                    btnTimerStart.visibility = View.GONE
                    btnTimerPause.visibility = View.GONE
                    btnTimerResume.visibility = View.VISIBLE
                }

                TimerService.TimerStatus.Running -> {
                    btnTimerStart.visibility = View.GONE
                    btnTimerPause.visibility = View.VISIBLE
                    btnTimerResume.visibility = View.GONE
                    timerDurationInput.visibility = View.GONE
                }

                TimerService.TimerStatus.Stopped -> {
                    btnTimerStart.visibility = View.VISIBLE
                    btnTimerPause.visibility = View.GONE
                    btnTimerResume.visibility = View.GONE
                }
            }
        }
    }
}
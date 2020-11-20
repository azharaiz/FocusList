package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import adil.dev.lib.materialnumberpicker.dialog.NumberPickerDialog
import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentTimerBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.services.TimerService
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.TimerViewModel
import kotlinx.android.synthetic.main.fragment_timer.*
import kotlinx.android.synthetic.main.fragment_timer.view.*


class TimerFragment : Fragment() {
    private lateinit var mService: TimerService
    private var mBound: Boolean = false

    private lateinit var runningReceiver: BroadcastReceiver

    private lateinit var binding: FragmentTimerBinding
    private val mTimerViewModel: TimerViewModel by activityViewModels()

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as TimerService.TimerBinder
            mService = binder.getService()
            mBound = true
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

        binding = FragmentTimerBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = requireActivity()
        binding.timerViewModel = mTimerViewModel

        val view = binding.root

        view.btnTimerStart.setOnClickListener {
            if (mBound) {
                mTimerViewModel.todoTitle.value?.let { it1 -> mService.setTodoTitle(it1) }
                mService.setDuration(timerDurationInput.text.toString().toInt())
                mService.startTimer()
            }
            mTimerViewModel.start()
        }

        view.btnTimerStop.setOnClickListener {
            if (mBound) {
                mService.stopTimer()
            }
            mTimerViewModel.stop()
            mTimerViewModel.todoTitle.value = ""
        }

        view.timerSetTimeButton.setOnClickListener {
            val dialog = NumberPickerDialog(
                requireActivity(), 0, 60
            ) { value ->
                mTimerViewModel.updateDuration(value)
            }
            dialog.show()
        }

        view.btnTimerPause.setOnClickListener {
            mTimerViewModel.pause()
            if (mBound) {
                mService.pauseTimer()
            }
        }

        view.btnTimerResume.setOnClickListener {
            mTimerViewModel.start()
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
                mTimerViewModel.timerTick.value = timeRemaining
                if (timeRemaining == 0) {
                    mTimerViewModel.stop()
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
}
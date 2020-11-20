package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.TimerViewModel

class TimerActivity : BaseActivity() {
    private lateinit var mTimerViewModel: TimerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mTimerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        mTimerViewModel.timerDuration.value = intent.extras?.get("TODO_DURATION") as Int?
    }

    override fun layoutId(): Int {
        return R.layout.activity_timer
    }

    override fun actionId(): Int {
        return R.id.action_timer
    }
}
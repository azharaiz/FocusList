package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.utils

import android.view.View
import androidx.databinding.InverseMethod
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.helpers.TimerHelper
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.services.TimerService

object Converter {
    @InverseMethod("toInt")
    fun toString(duration: Int): String {
        return if (duration == 0) "" else duration.toString()
    }

    fun showDuration(duration: Int): String {
        return "Duration: ${duration.toString()} minutes"
    }

    fun toTimerMode(duration: Int): String {
        return TimerHelper.displayTimer(duration)
    }

    fun startButton(timerService: TimerService): Int {
        return if (timerService.equals(TimerService.TimerStatus.Stopped))
            View.VISIBLE else View.GONE
    }

    fun pauseButton(timerService: TimerService): Int {
        return if (timerService.equals((TimerService.TimerStatus.Running))) View.VISIBLE else View.GONE
    }

    fun resumeButton(timerService: TimerService): Int {
        return if (timerService.equals((TimerService.TimerStatus.Paused))) View.VISIBLE else View.GONE
    }

    fun toInt(string: String): Int {
        return if (string.isEmpty()) 0 else string.toInt()
    }
}
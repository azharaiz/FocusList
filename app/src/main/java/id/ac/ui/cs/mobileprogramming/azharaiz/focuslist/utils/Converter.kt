package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.utils

import androidx.databinding.InverseMethod
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.helpers.TimerHelper

object Converter {
    @InverseMethod("toInt")
    fun toString(duration: Int): String {
        return if (duration == 0) "" else duration.toString()
    }

    fun showDuration(duration: Int): String {
        return if (duration == 0) "Duration: None" else "Duration: ${duration.toString()} minutes"
    }

    fun toTimerMode(duration: Int): String {
        return TimerHelper.displayTimer(duration)
    }


    fun toInt(string: String): Int {
        return if (string.isEmpty()) 0 else string.toInt()
    }
}
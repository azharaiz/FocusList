package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.helpers

import kotlin.math.floor

class TimerHelper {
    companion object {
        fun displayTimer(totalTimeInSeconds: Int): String {
            val minutes = floor((totalTimeInSeconds / 60).toDouble()).toInt()
            val seconds = totalTimeInSeconds % 60
            if (seconds < 10) {
                return "${minutes}:0${seconds}"
            }
            return "${minutes}:${seconds}"
        }
    }
}
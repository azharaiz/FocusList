package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.utils

import androidx.databinding.InverseMethod

object Converter {
    @InverseMethod("toInt")
    fun toString(duration: Int): String {
        return if (duration == 0) "" else duration.toString()
    }

    fun toInt(string: String): Int {
        return if (string.isEmpty()) 0 else string.toInt()
    }
}
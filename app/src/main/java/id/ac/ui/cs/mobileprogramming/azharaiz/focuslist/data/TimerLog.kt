package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timer_log_table")
data class TimerLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val duration: Int, // Duration in seconds
    val todoTitle: String
)
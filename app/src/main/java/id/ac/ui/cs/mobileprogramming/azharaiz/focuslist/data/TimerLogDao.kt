package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TimerLogDao {
    @Insert()
    suspend fun insertTimerLog(log: TimerLog)

    @Update
    suspend fun updateTimerLog(log: TimerLog)

    @Delete
    suspend fun deleteTimerLog(log: TimerLog)

    @Query("SELECT * FROM timer_log_table")
    fun readAllTimerLogs(): LiveData<List<TimerLog>>
}
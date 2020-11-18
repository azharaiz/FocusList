package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.repositories

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.TimerLog
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.TimerLogDao

class TimerLogRepository(private val timerLogDao: TimerLogDao) {
    val readAllTimerLogs: LiveData<List<TimerLog>> = timerLogDao.readAllTimerLogs()

    suspend fun insertTimerLog(log: TimerLog) {
        timerLogDao.insertTimerLog(log)
    }

    suspend fun updateTimerLog(log: TimerLog) {
        timerLogDao.updateTimerLog(log)
    }

    suspend fun deleteTimerLog(log: TimerLog) {
        timerLogDao.deleteTimerLog(log)
    }
}
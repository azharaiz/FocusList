package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.AppDatabase
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.TimerLog
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.repositories.TimerLogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimerLogViewModel(application: Application) : AndroidViewModel(application) {
    val readAllTimerLogs: LiveData<List<TimerLog>>
    private val repository: TimerLogRepository
    val timerDuration: MutableLiveData<Int> = MutableLiveData(0)

    init {
        val timerLogDao = AppDatabase.getDatabase(application).timerLogDao()
        repository = TimerLogRepository(timerLogDao)
        readAllTimerLogs = repository.readAllTimerLogs
    }

    fun insertTimerLog(log: TimerLog) {
        viewModelScope.launch(Dispatchers.IO) { repository.insertTimerLog(log) }
    }

    fun updateTimerLog(log: TimerLog) {
        viewModelScope.launch(Dispatchers.IO) { repository.updateTimerLog(log) }
    }

    fun deleteTimerLog(log: TimerLog) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteTimerLog(log) }
    }
}
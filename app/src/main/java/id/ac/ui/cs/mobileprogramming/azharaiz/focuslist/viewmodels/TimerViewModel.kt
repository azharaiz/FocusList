package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    var timerDuration: MutableLiveData<Int> = MutableLiveData(25)
    var timerTick: MutableLiveData<Int> = MutableLiveData(25 * 60)

    var timerStatusRunning: MutableLiveData<Boolean> = MutableLiveData(false)
    var timerStatusStopped: MutableLiveData<Boolean> = MutableLiveData(true)
    var timerStatusPaused: MutableLiveData<Boolean> = MutableLiveData(false)

    var todoTitle: MutableLiveData<String> = MutableLiveData("")

    fun updateDuration(duration: Int) {
        timerDuration.value = duration
        timerTick.value = duration.times(60)
    }

    fun start() {
        timerStatusRunning.value = true
        timerStatusStopped.value = false
        timerStatusPaused.value = false
    }

    fun pause() {
        timerStatusRunning.value = false
        timerStatusStopped.value = false
        timerStatusPaused.value = true
    }

    fun stop() {
        timerStatusRunning.value = false
        timerStatusStopped.value = true
        timerStatusPaused.value = false
    }
}
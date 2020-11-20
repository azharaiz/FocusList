package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    var timerDuration: MutableLiveData<Int> = MutableLiveData(25)
    var timerTick: MutableLiveData<Int> = MutableLiveData(0)

    fun updateDuration(duration: Int) {
        timerDuration.value = duration
        timerTick.value = duration.times(60)
    }
}
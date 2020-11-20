package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    var timerDuration: MutableLiveData<Int> = MutableLiveData(25)
}
package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    var userEmail: MutableLiveData<String> = MutableLiveData("")
    var isVerified: MutableLiveData<Boolean> = MutableLiveData(false)
}
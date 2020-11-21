package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RewardSelectViewModel : ViewModel() {
    var rewardId: MutableLiveData<Int> = MutableLiveData(0)
    var rewardTitle: MutableLiveData<String> = MutableLiveData("Reward: None")
    var rewardDesc: MutableLiveData<String> = MutableLiveData("")
    var rewardImg: MutableLiveData<String> = MutableLiveData("")
}
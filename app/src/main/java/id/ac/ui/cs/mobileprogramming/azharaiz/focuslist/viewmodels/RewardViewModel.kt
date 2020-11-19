package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.AppDatabase
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Reward
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.repositories.RewardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RewardViewModel(application: Application) : AndroidViewModel(application) {

    val readAllRewards: LiveData<List<Reward>>
    private val repository: RewardRepository


    lateinit var rewardId: MutableLiveData<Int>
    lateinit var rewardTitle: MutableLiveData<String>
    lateinit var rewardDescription: MutableLiveData<String>

    private lateinit var reward: Reward

    init {
        val rewardDao = AppDatabase.getDatabase(application).rewardDao()
        repository = RewardRepository(rewardDao)
        readAllRewards = repository.readAllRewards
        clearData()
    }

    fun clearData() {
        rewardId = MutableLiveData(0)
        rewardTitle = MutableLiveData("")
        rewardDescription = MutableLiveData("")
    }

    fun create() {
        reward = Reward(0, rewardTitle.value!!, rewardDescription.value!!)
        addReward(reward)
        clearData()
    }

    fun update() {
        reward = Reward(rewardId.value!!, rewardTitle.value!!, rewardDescription.value!!)
        updateReward(reward)
        clearData()
    }

    fun delete() {
        reward = Reward(rewardId.value!!, rewardTitle.value!!, rewardDescription.value!!)
        deleteReward(reward)
        clearData()
    }

    fun addReward(reward: Reward) {
        viewModelScope.launch(Dispatchers.IO) { repository.addReward(reward) }
    }

    fun updateReward(reward: Reward) {
        viewModelScope.launch(Dispatchers.IO) { repository.updateReward(reward) }
    }

    fun deleteReward(reward: Reward) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteReward(reward) }
    }

    fun deleteAllRewards() {
        viewModelScope.launch { repository.deleteAllRewards() }
    }
}
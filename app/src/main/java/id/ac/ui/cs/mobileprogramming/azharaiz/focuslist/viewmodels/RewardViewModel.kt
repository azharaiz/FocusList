package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.AppDatabase
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Reward
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.repositories.RewardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RewardViewModel(application: Application) : AndroidViewModel(application) {

    val readAllRewards: LiveData<List<Reward>>
    private val repository: RewardRepository

    init {
        val rewardDao = AppDatabase.getDatabase(application).rewardDao()
        repository = RewardRepository(rewardDao)
        readAllRewards = repository.readAllRewards
    }

    fun addReward(reward: Reward) {
        viewModelScope.launch(Dispatchers.IO) { repository.addReward(reward) }
    }

    fun updateReward(reward: Reward) {
        viewModelScope.launch(Dispatchers.IO) { repository.updateReward(reward) }
    }

    fun deleteTodo(reward: Reward) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteReward(reward) }
    }

    fun deleteAllTodo() {
        viewModelScope.launch { repository.deleteAllRewards() }
    }
}
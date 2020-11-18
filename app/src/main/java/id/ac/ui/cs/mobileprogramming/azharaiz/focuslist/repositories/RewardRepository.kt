package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.repositories

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Reward
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.RewardDao

class RewardRepository(private val rewardDao: RewardDao) {

    val readAllRewards: LiveData<List<Reward>> = rewardDao.readAllRewards()

    suspend fun addReward(reward: Reward) {
        rewardDao.addReward(reward)
    }

    suspend fun updateReward(reward: Reward) {
        rewardDao.updateReward(reward)
    }

    suspend fun deleteReward(reward: Reward) {
        rewardDao.deleteReward(reward)
    }

    suspend fun deleteAllRewards() {
        rewardDao.deleteAllRewards()
    }
}
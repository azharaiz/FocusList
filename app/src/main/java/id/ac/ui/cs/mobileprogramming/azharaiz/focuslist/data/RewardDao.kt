package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RewardDao {
    @Insert
    suspend fun addReward(reward: Reward)

    @Update
    suspend fun updateReward(reward: Reward)

    @Delete
    suspend fun deleteReward(reward: Reward)

    @Query("DELETE FROM reward_table")
    suspend fun deleteAllRewards()

    @Query("SELECT * FROM reward_table")
    fun readAllRewards(): LiveData<List<Reward>>
}
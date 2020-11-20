package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.RewardViewModel

class RewardActivity : BaseActivity() {

    private lateinit var mRewardViewModel: RewardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRewardViewModel = ViewModelProvider(this).get(RewardViewModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.activity_reward
    }

    override fun actionId(): Int {
        return R.id.action_reward
    }

    fun clickAddReward(view: View) {
        mRewardViewModel.clearData()
        findNavController(R.id.fragment_nav_reward_landscape)
            .navigate(R.id.action_rewardDetailFragment_to_rewardAddFragment2)
    }
}
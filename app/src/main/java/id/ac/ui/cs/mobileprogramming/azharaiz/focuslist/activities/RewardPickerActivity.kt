package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.adapters.RewardPickerAdapter
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Reward
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.ActivityRewardPickerBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments.RewardStatusListener
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.RewardViewModel

class RewardPickerActivity : AppCompatActivity(), RewardStatusListener {
    private lateinit var mRewardViewModel: RewardViewModel
    private lateinit var binding: ActivityRewardPickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reward_picker)
        mRewardViewModel = ViewModelProvider(this).get(RewardViewModel::class.java)

        binding.lifecycleOwner = this
        binding.rewardViewModel = mRewardViewModel

        val recyclerView: RecyclerView = findViewById(R.id.recyler_view_reward_picker)
        val adapter = RewardPickerAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        mRewardViewModel.readAllRewards.observe(this, { rewards ->
            adapter.setData(rewards)
        })
    }

    override fun onRewardClicked(reward: Reward) {
        val returnIntent = Intent()
        returnIntent.putExtra("REWARD_ID", reward.id)
        returnIntent.putExtra("REWARD_TITLE", reward.title)
        returnIntent.putExtra("REWARD_DESC", reward.description)
        returnIntent.putExtra("REWARD_IMG", reward.imageUrl)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun checkOrientation(): Int {
        return resources.configuration.orientation
    }
}
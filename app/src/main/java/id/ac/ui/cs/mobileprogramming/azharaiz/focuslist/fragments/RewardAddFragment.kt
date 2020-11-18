package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Reward
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.RewardViewModel
import kotlinx.android.synthetic.main.fragment_reward_add.view.*

class RewardAddFragment : Fragment() {
    private lateinit var mRewardViewModel: RewardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reward_add, container, false)

        view.btnAddReward.setOnClickListener {
            if (!TextUtils.isEmpty(view.inputRewardTitle.text.toString())) {
                findNavController().navigate(R.id.action_rewardAddFragment_to_rewardListFragment)
                addNewReward(view)
            } else {
                view.inputRewardTitle.error = "Please input reward title"
            }
        }

        return view
    }

    private fun addNewReward(view: View) {
        val title = view.inputRewardTitle.text.toString()
        val description = view.inputRewardDescription.text.toString()

        val reward = Reward(0, title, description)
        mRewardViewModel = ViewModelProvider(this).get(RewardViewModel::class.java)

        mRewardViewModel.addReward(reward)
    }
}
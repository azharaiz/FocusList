package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.adapters.RewardViewAdapter
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Reward
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.RewardViewModel

class RewardListFragment : Fragment(), RewardStatusListener {

    private val mRewardViewModel: RewardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_reward_list, container, false)

        val btnAdd =
            view.findViewById<FloatingActionButton>(R.id.rewardFloatingAction)

        val recyclerView: RecyclerView = view.findViewById(R.id.reward_list_recycler_view)
        val adapter = RewardViewAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)


        mRewardViewModel.readAllRewards.observe(viewLifecycleOwner, { rewards ->
            adapter.setData(rewards)
        })

        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_rewardListFragment_to_rewardAddFragment)
        }
        return view
    }

    override fun onRewardClicked(reward: Reward) {
        mRewardViewModel.updateData(reward)
    }
}

interface RewardStatusListener {
    fun onRewardClicked(reward: Reward)
}
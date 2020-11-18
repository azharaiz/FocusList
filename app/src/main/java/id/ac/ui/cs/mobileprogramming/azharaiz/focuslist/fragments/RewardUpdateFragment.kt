package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.Reward
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.RewardViewModel
import kotlinx.android.synthetic.main.fragment_reward_update.*
import kotlinx.android.synthetic.main.fragment_reward_update.view.*

class RewardUpdateFragment : Fragment() {

    private val args by navArgs<RewardUpdateFragmentArgs>()

    private lateinit var mRewardViewModel: RewardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_reward_update, container, false)

        mRewardViewModel = ViewModelProvider(this).get(RewardViewModel::class.java)

        view.inputRewardUpdateTitle.setText(args.currentReward.title)
        view.inputRewardUpdateDescription.setText(args.currentReward.description)

        view.btnUpdateReward.setOnClickListener {
            updateItem()
        }

        view.btnDeleteReward.setOnClickListener {
            deleteItem(args.currentReward)
        }

        return view
    }

    private fun updateItem() {
        val title = inputRewardUpdateTitle.text
        val description = inputRewardUpdateDescription.text

        if (!TextUtils.isEmpty(title)) {
            val updatedReward =
                Reward(args.currentReward.id, title.toString(), description.toString())

            mRewardViewModel.updateReward(updatedReward)

            Toast.makeText(requireContext(), "Update success", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_rewardUpdateFragment_to_rewardListFragment)
        }
    }

    private fun deleteItem(reward: Reward) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mRewardViewModel.deleteTodo(reward)
            findNavController().navigate(R.id.action_rewardUpdateFragment_to_rewardListFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentReward.title}?")
        builder.setTitle("Are you sure you want to delete ${args.currentReward.title}?")
        builder.create().show()
    }
}
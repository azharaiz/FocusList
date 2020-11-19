package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentRewardUpdateBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.RewardViewModel

class RewardUpdateFragment : Fragment() {

    private val args by navArgs<RewardUpdateFragmentArgs>()

    private lateinit var mRewardViewModel: RewardViewModel
    private lateinit var binding: FragmentRewardUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRewardUpdateBinding.inflate(inflater, container, false)

        mRewardViewModel = ViewModelProvider(this).get(RewardViewModel::class.java)

        mRewardViewModel.rewardId.value = args.currentReward.id
        mRewardViewModel.rewardTitle.value = args.currentReward.title
        mRewardViewModel.rewardDescription.value = args.currentReward.description

        binding.lifecycleOwner = requireActivity()
        binding.rewardViewModel = mRewardViewModel

        binding.btnUpdateReward.setOnClickListener {
            mRewardViewModel.update()
            findNavController().navigate(R.id.action_rewardUpdateFragment_to_rewardListFragment)
        }

        binding.btnDeleteReward.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                mRewardViewModel.delete()
                findNavController().navigate(R.id.action_rewardUpdateFragment_to_rewardListFragment)
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${mRewardViewModel.rewardTitle.value}?")
            builder.setTitle("Are you sure you want to delete ${mRewardViewModel.rewardDescription.value}?")
            builder.create().show()
        }

        return binding.root
    }
}
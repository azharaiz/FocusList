package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.app.AlertDialog
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentRewardUpdateBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.RewardViewModel

class RewardUpdateFragment : Fragment() {

    private val mRewardViewModel: RewardViewModel by activityViewModels()
    private lateinit var binding: FragmentRewardUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRewardUpdateBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = requireActivity()
        binding.rewardViewModel = mRewardViewModel

        binding.btnUpdateReward.setOnClickListener {
            mRewardViewModel.update()
            navigateBack()
        }

        binding.btnDeleteReward.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                mRewardViewModel.delete()
                navigateBack()
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${mRewardViewModel.rewardTitle.value}?")
            builder.setTitle("Are you sure you want to delete ${mRewardViewModel.rewardTitle.value}?")
            builder.create().show()
        }

        return binding.root
    }

    private fun navigateBack() {
        if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            findNavController().navigate(R.id.action_rewardUpdateFragment_to_rewardListFragment)
        } else {
            findNavController().navigate(R.id.action_rewardUpdateFragment2_to_rewardDetailFragment)
        }
    }
}
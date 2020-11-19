package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentRewardAddBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.RewardViewModel

class RewardAddFragment : Fragment() {
    private lateinit var mRewardViewModel: RewardViewModel
    private lateinit var binding: FragmentRewardAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRewardAddBinding.inflate(inflater, container, false)

        mRewardViewModel = ViewModelProvider(this).get(RewardViewModel::class.java)

        binding.lifecycleOwner = requireActivity()
        binding.rewardViewModel = mRewardViewModel

        binding.btnAddReward.setOnClickListener {
            findNavController().navigate(R.id.action_rewardAddFragment_to_rewardListFragment)
            mRewardViewModel.create()
        }

        return binding.root
    }
}
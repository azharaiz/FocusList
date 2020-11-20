package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentRewardDetailBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.RewardViewModel

class RewardDetailFragment : Fragment() {
    private val mRewardViewModel: RewardViewModel by activityViewModels()
    private lateinit var binding: FragmentRewardDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRewardDetailBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = requireActivity()
        binding.rewardViewModel = mRewardViewModel

        binding.rewardDetailEditBtn.setOnClickListener {
            findNavController().navigate(R.id.action_rewardDetailFragment_to_rewardUpdateFragment2)
        }

        return binding.root
    }
}
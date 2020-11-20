package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentRewardAddBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.RewardViewModel

class RewardAddFragment : Fragment() {
    private val mRewardViewModel: RewardViewModel by activityViewModels()
    private lateinit var binding: FragmentRewardAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRewardAddBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = requireActivity()
        binding.rewardViewModel = mRewardViewModel

        binding.btnAddReward.setOnClickListener {
            mRewardViewModel.create()
            addRewardNavigateBack()
        }

        binding.addRewardImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 20)
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 20) {
            if (data != null) {
                val uri = data.data
                mRewardViewModel.rewardImageUrl.value = uri.toString()
            }
        }
    }

    private fun addRewardNavigateBack() {
        if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            findNavController().navigate(R.id.action_rewardAddFragment_to_rewardListFragment)
        } else {
            findNavController().navigate(R.id.action_rewardAddFragment2_to_rewardDetailFragment)
        }
    }
}
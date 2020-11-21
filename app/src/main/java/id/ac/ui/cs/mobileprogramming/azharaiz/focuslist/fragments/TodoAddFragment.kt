package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import adil.dev.lib.materialnumberpicker.dialog.NumberPickerDialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities.RewardPickerActivity
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentAddTodoBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.RewardViewModel
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.TodoViewModel

class TodoAddFragment : Fragment() {

    private lateinit var mTodoViewModel: TodoViewModel
    private lateinit var binding: FragmentAddTodoBinding

    private val mRewardViewModel: RewardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTodoBinding.inflate(inflater, container, false)
        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        binding.lifecycleOwner = requireActivity()
        binding.todoViewModel = mTodoViewModel

        binding.addTodoBtn.setOnClickListener {
            mTodoViewModel.create()
            addTodoNavigateBack()
        }

        binding.addTodoSetDurationButton.setOnClickListener {
            val dialog = NumberPickerDialog(
                requireActivity(), 0, 60
            ) { value ->
                mTodoViewModel.todoDuration.value = value
            }
            dialog.show()
        }

        binding.addTodoSelectRewardButton.setOnClickListener {
            val intent = Intent(requireActivity(), RewardPickerActivity::class.java)
            startActivityForResult(intent, 30)
        }

        return binding.root
    }

    private fun addTodoNavigateBack() {
        if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            findNavController().navigate(R.id.action_addTodoFragment_to_todoListFragment)
        } else {
            findNavController().navigate(R.id.action_todoAddFragment_to_todoDetailFragment)
        }
    }
}
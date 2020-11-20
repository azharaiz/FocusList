package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities.TimerActivity
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentTodoDetailBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.TodoViewModel

class TodoDetailFragment : Fragment() {
    private lateinit var binding: FragmentTodoDetailBinding
    private val mTodoViewModel: TodoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoDetailBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = requireActivity()
        binding.todoViewModel = mTodoViewModel


        binding.btnEditTodo.setOnClickListener {
            if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                findNavController().navigate(R.id.action_todoDetailFragment_to_todoUpdateFragment2)
            } else {
                findNavController().navigate(R.id.action_todoDetailFragment2_to_todoUpdateFragment)
            }
        }

        binding.buttonStartTimer.setOnClickListener {
            val intent = Intent(requireActivity(), TimerActivity::class.java)
            intent.putExtra("TODO_DURATION", mTodoViewModel.todoDuration.value)
            intent.putExtra("TODO_TITLE", mTodoViewModel.todoTitle.value)
            startActivity(intent)
        }


        return binding.root
    }
}
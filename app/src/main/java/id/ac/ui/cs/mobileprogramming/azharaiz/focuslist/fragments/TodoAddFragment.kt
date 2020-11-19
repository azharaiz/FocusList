package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentAddTodoBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.TodoViewModel

class TodoAddFragment : Fragment() {

    private lateinit var mTodoViewModel: TodoViewModel
    private lateinit var binding: FragmentAddTodoBinding

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
            findNavController().navigate(R.id.action_addTodoFragment_to_todoListFragment)
        }

        return binding.root
    }
}
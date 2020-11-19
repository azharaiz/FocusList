package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentTodoUpdateBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.todo.TodoUpdateViewModel

class TodoUpdateFragment : Fragment() {

    private val args by navArgs<TodoUpdateFragmentArgs>()

    private lateinit var mTodoUpdateViewModel: TodoUpdateViewModel
    private lateinit var binding: FragmentTodoUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoUpdateBinding.inflate(inflater, container, false)

        mTodoUpdateViewModel = ViewModelProvider(this).get(TodoUpdateViewModel::class.java)

        mTodoUpdateViewModel.todoId.value = args.currentTodo.id
        mTodoUpdateViewModel.todoTitle.value = args.currentTodo.title
        mTodoUpdateViewModel.todoStatus.value = args.currentTodo.status

        binding.lifecycleOwner = requireActivity()
        binding.todoUpdateViewModel = mTodoUpdateViewModel


        binding.updateTodoBtn.setOnClickListener {
            mTodoUpdateViewModel.save()
            Toast.makeText(requireContext(), "Update success", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_todoUpdateFragment_to_todoListFragment)
        }

        binding.deleteTodoBtn.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                mTodoUpdateViewModel.delete()
                findNavController().navigate(R.id.action_todoUpdateFragment_to_todoListFragment)
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${args.currentTodo.title}?")
            builder.setTitle("Are you sure you want to delete ${args.currentTodo.title}?")
            builder.create().show()
        }

        return binding.root
    }
}
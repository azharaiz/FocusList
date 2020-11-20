package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.app.AlertDialog
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentTodoUpdateBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.TodoViewModel

class TodoUpdateFragment : Fragment() {

    val TAG = "TODO_UPDATE_FRAGMENT"

    private val args by navArgs<TodoUpdateFragmentArgs>()

    private val mTodoViewModel: TodoViewModel by activityViewModels()
    private lateinit var binding: FragmentTodoUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoUpdateBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = requireActivity()
        binding.todoViewModel = mTodoViewModel

        binding.updateTodoBtn.setOnClickListener {
            mTodoViewModel.update()
            Toast.makeText(requireContext(), "Update success", Toast.LENGTH_SHORT).show()
            Log.i(TAG, mTodoViewModel.todoTitle.value.toString())
            if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                mTodoViewModel.update()
                findNavController().navigate(R.id.action_todoUpdateFragment_to_todoListFragment)
            }
            Log.i(TAG, mTodoViewModel.todoTitle.value.toString())
        }

        binding.deleteTodoBtn.setOnClickListener {
            Log.i(TAG, mTodoViewModel.todoTitle.value.toString())
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                mTodoViewModel.delete()
                if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    findNavController().navigate(R.id.action_todoUpdateFragment_to_todoListFragment)
                }
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${mTodoViewModel.todoTitle.value}?")
            builder.setTitle("Are you sure you want to delete ${mTodoViewModel.todoTitle.value}?")
            builder.create().show()
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "START")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "DESTROY")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "STOP")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "RESUME")
    }
}
package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import adil.dev.lib.materialnumberpicker.dialog.NumberPickerDialog
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
            navigateBack()
        }

        binding.deleteTodoBtn.setOnClickListener {
            Log.i(TAG, mTodoViewModel.todoTitle.value.toString())
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                mTodoViewModel.delete()
                navigateBack()
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${mTodoViewModel.todoTitle.value}?")
            builder.setTitle("Are you sure you want to delete ${mTodoViewModel.todoTitle.value}?")
            builder.create().show()
        }

        binding.updateTodoSetDurationButton.setOnClickListener {
            val dialog = NumberPickerDialog(
                requireActivity(), 0, 60
            ) { value ->
                mTodoViewModel.todoDuration.value = value
            }
            dialog.show()
        }

        return binding.root
    }

    private fun navigateBack() {
        if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            findNavController().navigate(R.id.action_todoUpdateFragment_to_todoListFragment)
        } else {
            findNavController().navigate(R.id.action_todoUpdateFragment2_to_todoDetailFragment)
        }
    }
}
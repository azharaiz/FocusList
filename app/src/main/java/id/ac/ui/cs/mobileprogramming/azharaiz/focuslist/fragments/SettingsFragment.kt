package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities.TodoActivity
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.databinding.FragmentSettingsBinding
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.SettingsViewModel
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment() {
    private val mSettingsViewModel: SettingsViewModel by activityViewModels()
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = requireActivity()
        binding.settingsViewModel = mSettingsViewModel

        val view = binding.root

        val user = Firebase.auth.currentUser

        mSettingsViewModel.userEmail.value = user!!.email
        mSettingsViewModel.isVerified.value = user.isEmailVerified


        view.btnEmailVerification.setOnClickListener {
            user.sendEmailVerification()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            context,
                            "Resend Verification Success",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        view.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            (activity as TodoActivity).openLoginPage()
        }
        return view
    }
}
package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities.TodoActivity
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_settings, container, false)

        val user = Firebase.auth.currentUser

        user?.let {
            view.settingsEmail.text = user.email
            if (user.isEmailVerified) {
                view.settingsEmailVerification.text = resources.getText(R.string.email_verified)
                view.btnEmailVerification.visibility = View.GONE
            } else {
                view.settingsEmailVerification.text = resources.getText(R.string.email_not_verified)
                view.settingsEmailVerification
                    .setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                view.btnEmailVerification.visibility = View.VISIBLE
            }
        }

        view.btnEmailVerification.setOnClickListener {
            user!!.sendEmailVerification()
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
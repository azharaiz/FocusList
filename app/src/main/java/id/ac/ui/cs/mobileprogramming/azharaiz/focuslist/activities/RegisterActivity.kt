package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val TAG = "REGISTER_ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth
    }

    fun registerUser(view: View) {
        val inputEmail: EditText = findViewById(R.id.inputRegisterEmail)
        val inputPassword: EditText = findViewById(R.id.inputRegisterPassword)
        val inputConfirmPassword: EditText = findViewById(R.id.inputRegisterConfirmPassword)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        val email: String = inputEmail.text.toString().trim()
        val password: String = inputPassword.text.toString().trim()
        val confirmPassword: String = inputConfirmPassword.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            inputEmail.error = "Please enter your email"
            return
        }

        if (TextUtils.isEmpty(password)) {
            inputPassword.error = "Please enter your password"
            return
        }

        if (password.length < 6) {
            inputPassword.error = "Password too short"
            return
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            inputConfirmPassword.error = "Please retype your password"
            return
        }

        progressBar.visibility = View.VISIBLE


        if (!password.equals(confirmPassword)) {
            inputConfirmPassword.error = "Please retype correct password"
            return
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    progressBar.visibility = View.GONE
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}
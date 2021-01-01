package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val TAG = "LOGIN_ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
    }

    fun openRegisterActivity(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun loginUser(view: View) {
        if (!isNetworkConnected()) {
            Toast.makeText(applicationContext, "Please connect to internet", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val inputEmail: EditText = findViewById(R.id.inputLoginEmail)
        val inputPassword: EditText = findViewById(R.id.inputLoginPassword)
        val progressBar: ProgressBar = findViewById(R.id.loginProgressBar)

        val email: String = inputEmail.text.toString().trim()
        val password: String = inputPassword.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            inputEmail.error = "Please enter your email"
            return
        }

        if (TextUtils.isEmpty(password)) {
            inputPassword.error = "Please enter your password"
            return
        }

        progressBar.visibility = View.VISIBLE

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val intent = Intent(this, TodoActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    val toast = Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    )
                    toast.setGravity(Gravity.BOTTOM, 0, 400)
                    toast.show()
                    progressBar.visibility = View.GONE
                }
            }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}
package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R

class TodoActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth
    private val TAG = "MAIN_ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        findViewById<FloatingActionButton>(R.id.btnAdd).setOnClickListener {
//            findNavController(this).navigate()
//        }

        auth = Firebase.auth
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Log.i(TAG, "User logged in")
        } else {
            openLoginPage()
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_todo
    }

    override fun actionId(): Int {
        return R.id.action_todo
    }

    fun openLoginPage() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
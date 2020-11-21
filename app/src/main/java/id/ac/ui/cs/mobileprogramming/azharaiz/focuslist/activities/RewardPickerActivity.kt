package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R

class RewardPickerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward_picker)

        findViewById<Button>(R.id.button).setOnClickListener {
            val returnIntent = Intent()
            returnIntent.extras?.putString("result", "hello")
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}
package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities

import android.content.Intent
import android.view.View
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R

class SettingsActivity : BaseActivity() {

    override fun layoutId(): Int {
        return R.layout.activity_settings
    }

    override fun actionId(): Int {
        return R.id.action_settings
    }

    fun navigateToVisualActivity(view: View) {
        val intent = Intent(this, VisualActivity::class.java)
        startActivity(intent)
    }
}
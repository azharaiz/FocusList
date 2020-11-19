package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R


abstract class BaseActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("BASE", "DESTROYED")
    }

    override fun onStart() {
        super.onStart()
        updateNavigationBarState()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_todo ->
                startActivity(Intent(this, TodoActivity::class.java))
            R.id.action_reward ->
                startActivity(Intent(this, RewardActivity::class.java))
            R.id.action_timer ->
                startActivity(Intent(this, TimerActivity::class.java))
            R.id.action_settings ->
                startActivity(Intent(this, SettingsActivity::class.java))
        }
        finish()
        return true
    }

    private fun updateNavigationBarState() {
        val item: MenuItem = bottomNavigationView.menu.findItem(actionId())
        item.isChecked = true
    }

    abstract fun layoutId(): Int
    abstract fun actionId(): Int
}
package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.visual.MyGLSurfaceView

class VisualActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val glView = MyGLSurfaceView(this)
        setContentView(glView)
    }
}
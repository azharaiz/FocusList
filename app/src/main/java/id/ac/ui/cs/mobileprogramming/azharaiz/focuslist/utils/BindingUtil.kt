package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imgUri", "placeholder")
fun loadImage(view: ImageView, url: String?, placeholder: Drawable) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view).load(Uri.parse(url)).into(view)
    } else {
        view.setImageDrawable(placeholder)
    }
}
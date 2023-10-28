package com.mta.newsapp.ext

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun ImageView.changeColorTint(@ColorRes colorId: Int) {
  setColorFilter(
    ContextCompat.getColor(context, colorId), android.graphics.PorterDuff.Mode.MULTIPLY
  )
}
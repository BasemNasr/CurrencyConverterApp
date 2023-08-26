package com.currency.converter.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import coil.load
import coil.transform.RoundedCornersTransformation


fun ImageView.loadImageFromInternet(
    imageUrl: String?,
    errorImage: Int? = null,
    //errorPlaceholder: Drawable? = null,
    roundedCorner: Float = 0f,
    allowHardware: Boolean = true
) {
    this.load(imageUrl) {
        crossfade(true)
        crossfade(500)
        RoundedCornersTransformation(roundedCorner)
        allowHardware(allowHardware)
        error(errorImage?.let { ResourcesCompat.getDrawable(resources, it, null) })

    }
}

fun View.rotateImage(duration:Long=500) {
    val rotate = RotateAnimation(
        0f,
        180f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    )
    rotate.duration = duration
    rotate.interpolator = LinearInterpolator()
    this.startAnimation(rotate)
}


package com.currency.conventer.utils

import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import coil.load
import coil.transform.RoundedCornersTransformation


fun ImageView.loadImageFromInternet(
    imageUrl: String?,
    errorImage: Int? = null,
    //errorPlaceholder: Drawable? = null,
    roundedCorner: Float = 0f,
    allowHardware:Boolean=true
) {
    this.load(imageUrl) {
        crossfade(true)
        crossfade(500)
        RoundedCornersTransformation(roundedCorner)
        allowHardware(allowHardware)
        error(errorImage?.let { ResourcesCompat.getDrawable(resources, it, null) })

    }
}


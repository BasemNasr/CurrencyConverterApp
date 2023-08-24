package com.currency.conventer.data.models

import androidx.annotation.Keep
import java.io.Serializable

@Keep
open class BaseResponse(
    val status: String? = "",
    val message: String? = "",
    val code: String? = null,
)
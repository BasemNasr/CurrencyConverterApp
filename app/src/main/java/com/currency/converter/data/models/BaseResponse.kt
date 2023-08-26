package com.currency.converter.data.models

import androidx.annotation.Keep

@Keep
open class BaseResponse(
    val status: String? = "",
    val message: String? = "",
    val code: String? = null,
)
package com.currency.conventer.base.delegate

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View

interface EmptyErrorDelegate {
    fun showEmptyOrErrorView(
        context: Context,
        view: View,
        type: String,
        title: String? = null,
        body: String? = null,
        showBackToHomeBtn: Boolean = false,
        icon: Drawable? = null,
        onEmptyErrorBtnClicked: OnEmptyErrorBtnClicked? = null,
        btnTitle:String?=null
    )

    fun hideEmptyOrErrorView(view: View?)
}
interface OnEmptyErrorBtnClicked {
    fun onEmptyErrorBtnClicked()
}
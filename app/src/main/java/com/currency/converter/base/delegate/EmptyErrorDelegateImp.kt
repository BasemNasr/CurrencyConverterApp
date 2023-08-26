package com.currency.converter.base.delegate

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import coil.load
import com.currency.converter.R

class EmptyErrorDelegateImp : EmptyErrorDelegate {
    override fun showEmptyOrErrorView(
        context: Context,
        view: View,
        type: String,
        title: String?,
        body: String?,
        showBackToHomeBtn: Boolean,
        icon: Drawable?,
        onEmptyErrorBtnClicked: OnEmptyErrorBtnClicked?,
        btnTitle:String?,
    ) {
        val tvTitle: AppCompatTextView = view.findViewById(R.id.tvTitle)
        val tvBody: AppCompatTextView = view.findViewById(R.id.tvBody)
        val ivIcon: AppCompatImageView = view.findViewById(R.id.ivIcon)
        val btnBack: AppCompatButton = view.findViewById(R.id.btnBackToHome)


        when (type) {
            EmptyErrorMessageType.EMPTY_LIST_VIEW.name -> {
                ivIcon.load(R.drawable.empty_data)
                tvTitle.text = context.getString(R.string.no_data_to_preview)
                tvBody.text = context.getString(R.string.no_data_to_preview)
            }
            EmptyErrorMessageType.SERVER_ERROR_VIEW.name -> {
                ivIcon.load(R.drawable.server_error)
                tvTitle.text = context.getString(R.string.server_error)
                tvBody.text = context.getString(R.string.server_error_body)
            }
            EmptyErrorMessageType.CONNECTION_LOST_VIEW.name -> {
                ivIcon.load(R.drawable.connection_error)
                tvTitle.text = context.getString(R.string.no_internet_connection)
                tvBody.text = context.getString(R.string.no_internet_connection_body)
            }
        }

        title?.let { tvTitle.text = title }
        body?.let { tvBody.text = body }
        icon?.let { ivIcon.load(icon) }
        btnTitle?.let { btnBack.text =btnTitle }



        view.visibility = View.VISIBLE

        if (showBackToHomeBtn) {
            btnBack.visibility = View.VISIBLE
            btnBack.setOnClickListener {
                onEmptyErrorBtnClicked?.onEmptyErrorBtnClicked()
            }
        } else {
            btnBack.visibility = View.INVISIBLE
        }

    }

    override fun hideEmptyOrErrorView(view: View?) {
        view?.let {
            view.visibility = View.GONE
        }
    }


}
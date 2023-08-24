@file:Suppress("UNCHECKED_CAST")

package com.currency.conventer.base

import android.app.UiModeManager
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding

abstract class ParentActivity<out VB : ViewBinding> : AppCompatActivity() {
    abstract val bindingInflater: (LayoutInflater) -> VB

    private var _binding: ViewBinding? = null
    protected val binding: VB
        get() = _binding as VB


    protected abstract fun initializeComponents()

    private val mContext by lazy { this@ParentActivity }


    private var uiModeManager: UiModeManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(_binding?.root)

        initializeComponents()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun changeStatusBarColor(color: Int, navigationBarColor: Int, fullscreen: Boolean = false) {
        window.statusBarColor = ContextCompat.getColor(this, color)
        window.navigationBarColor = ContextCompat.getColor(this, navigationBarColor)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            window.navigationBarDividerColor = ContextCompat.getColor(this, color)



        if (fullscreen) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        }
    }


}
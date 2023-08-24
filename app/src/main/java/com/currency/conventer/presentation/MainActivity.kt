package com.currency.conventer.presentation

import android.view.LayoutInflater
import com.currency.conventer.base.ParentActivity
import com.currency.conventer.databinding.ActivityMainBinding

class MainActivity : ParentActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun initializeComponents() {

    }
}
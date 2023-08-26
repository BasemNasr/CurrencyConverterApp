package com.currency.converter.presentation.splash

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.currency.converter.base.ParentFragment
import com.currency.converter.databinding.FragmentSplashBinding
import com.currency.converter.presentation.MainNavigateEvent
import com.currency.converter.presentation.MainNavigationController


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashFragment : ParentFragment<FragmentSplashBinding>(){
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSplashBinding::inflate

    private val mNavigator: MainNavigationController by lazy {
        MainNavigationController(
            findNavController()
        )
    }

    @SuppressLint("SetTextI18n")
    override fun initializeComponents(view: View) {
        lifecycleScope.launch {
            delay(1500)
            mNavigator.navigate(MainNavigateEvent.SplashToHome)
        }
    }




}
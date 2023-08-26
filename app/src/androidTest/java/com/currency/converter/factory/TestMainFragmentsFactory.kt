package com.currency.converter.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.currency.converter.presentation.details.DetailsFragment
import com.currency.converter.presentation.home.HomeFragment
import com.currency.converter.presentation.splash.SplashFragment

class TestMainFragmentsFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            SplashFragment::class.java.name -> SplashFragment()
            HomeFragment::class.java.name -> HomeFragment()
            DetailsFragment::class.java.name -> DetailsFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}
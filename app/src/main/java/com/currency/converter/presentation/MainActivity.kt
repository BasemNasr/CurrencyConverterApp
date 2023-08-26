package com.currency.converter.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.currency.converter.R
import com.currency.converter.base.ParentActivity
import com.currency.converter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ParentActivity<ActivityMainBinding>(),
    NavController.OnDestinationChangedListener {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate


    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        navHostFragment.navController
    }

    override fun initializeComponents() {
        navController.addOnDestinationChangedListener(this@MainActivity)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.splashFragment -> binding.toolbar.visibility = View.GONE
            else -> binding.toolbar.visibility = View.VISIBLE
        }
    }
}
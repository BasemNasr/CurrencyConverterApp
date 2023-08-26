package com.currency.converter.presentation

import android.os.Bundle
import androidx.navigation.NavController
import com.currency.converter.base.navigation.NavigationController

import com.currency.converter.base.navigation.NavigationEventController
import com.currency.converter.presentation.home.HomeFragmentDirections
import com.currency.converter.presentation.splash.SplashFragmentDirections


class MainNavigationController
constructor(
    private val navController: NavController
) : NavigationController {
    override fun navigate(event: NavigationEventController) {
        try {
            when (event) {
                is MainNavigateEvent.SplashToHome -> {
                    navController.navigate(
                        SplashFragmentDirections.actionSplashFragmentToHomeFragment(),
                    )
                }

                is MainNavigateEvent.HomeToDetails -> {
                    navController.navigate(
                        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                            event.currencyFrom,
                            event.currencyTo,
                        ).actionId,

                        args = Bundle().apply {
                            putString("from", event.currencyFrom)
                            putString("to", event.currencyTo)
                        }
                    )
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

sealed class MainNavigateEvent : NavigationEventController() {
    object SplashToHome : MainNavigateEvent()
    data class HomeToDetails(val currencyFrom:String,val currencyTo:String) : MainNavigateEvent()
}
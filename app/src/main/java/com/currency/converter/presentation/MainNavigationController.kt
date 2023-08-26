package com.currency.converter.presentation

import androidx.navigation.NavController
import com.currency.converter.base.navigation.NavigationController

import com.currency.converter.base.navigation.NavigationEventController
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

                /*is MainNavigateEvent.HomeToHelpSheet -> {
                    navController.navigate(
                        HomeFragmentDirections.actionHomeFragmentToHelpSheetFragment(
                            event.support_number,
                            event.startLat,
                            event.startLng
                        ).actionId,

                        args = Bundle().apply {
                            putString("support_number", event.support_number)
                            putString("startLat", event.startLat)
                            putString("startLng", event.startLng)
                        }
                    )
                }*/
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

sealed class MainNavigateEvent : NavigationEventController() {
    object SplashToHome : MainNavigateEvent()

}
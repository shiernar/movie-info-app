package com.example.presentation.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.presentation.home.screen.HomeScreen

const val homeScreenNavigationRoute = "home"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeScreenNavigationRoute, navOptions)
}

fun NavGraphBuilder.Home() {
    composable(homeScreenNavigationRoute) {
        HomeScreen()
    }
}
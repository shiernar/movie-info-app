package com.example.presentation.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.presentation.home.screen.HomeScreen
import kotlinx.serialization.Serializable

//const val homeScreenNavigationRoute = "home"
@Serializable
object Home

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(Home, navOptions)
}

fun NavGraphBuilder.Home() {
    composable<Home> {
        HomeScreen()
    }
}

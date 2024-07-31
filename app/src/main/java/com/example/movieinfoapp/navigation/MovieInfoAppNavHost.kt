package com.example.movieinfoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.presentation.home.navigation.Home
import com.example.presentation.home.navigation.homeScreenNavigationRoute

@Composable
fun MovieInfoAppNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = homeScreenNavigationRoute
) {
    NavHost(navHostController, startDestination = startDestination, modifier = modifier) {
        Home()
    }
}

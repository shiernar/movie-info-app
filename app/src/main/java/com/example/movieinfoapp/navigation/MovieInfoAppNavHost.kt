package com.example.movieinfoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.presentation.home.navigation.Home
import kotlin.reflect.KClass

@Composable
fun MovieInfoAppNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: KClass<out Any> = Home::class.java.kotlin
) {
    NavHost(navHostController, startDestination = startDestination, modifier = modifier) {
        Home()
    }
}

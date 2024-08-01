package com.example.presentation.home.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.core.provider.FontsContractCompat.Columns
import com.example.presentation.home.movielist.screen.MovieListScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun HomeScreen() {

    val navigator = rememberListDetailPaneScaffoldNavigator<Int>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                MovieListScreen(
                    { movieId ->
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, movieId)
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                /*navigator.currentDestination?.content?.let { movieId ->
                    MyDetails(it)
                }*/
            }
        },
    )
}

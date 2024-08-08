package com.example.presentation.home.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.provider.FontsContractCompat.Columns
import com.example.presentation.home.movielist.screen.MovieListScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3Api::class)
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
                Surface(color = MaterialTheme.colorScheme.secondaryContainer) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "a")
                                },
                                navigationIcon = {
                                    IconButton(onClick = { navigator.navigateBack() }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Arrow Back"
                                        )
                                    }
                                },
                            )
                        }
                    ) { scaffoldPadding ->
                        Column(
                            modifier = Modifier.padding(scaffoldPadding),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            navigator.currentDestination?.content?.let { movieId ->
                                Text(movieId.toString())
                            }
                        }
                    }
                }
            }
        }
    )
}

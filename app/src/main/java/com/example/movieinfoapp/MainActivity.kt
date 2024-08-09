package com.example.movieinfoapp

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.common.ui.theme.AppTheme
import com.example.movieinfoapp.navigation.MovieInfoAppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle =
                SystemBarStyle.dark(Color.Transparent.toArgb())
            ,
            navigationBarStyle =
                SystemBarStyle.dark(Color.Transparent.toArgb())
        )
        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize()){
                    val navController = rememberNavController()
                    MovieInfoAppNavHost(navHostController = navController)
                }
            }
        }
    }
}

package com.example.common.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimensions(
    val paddingSmall: Dp = 4.dp,
    val paddingMedium: Dp = 8.dp,
    val paddingLarge: Dp = 24.dp,
    val photoMedium: Dp = 120.dp,
    val roundedCornerSmall: Dp = 16.dp,
    val roundedCornerMedium: Dp = 24.dp
)

internal val LocalDimensions = staticCompositionLocalOf { AppDimensions() }


val MaterialTheme.dimensions: AppDimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalDimensions.current

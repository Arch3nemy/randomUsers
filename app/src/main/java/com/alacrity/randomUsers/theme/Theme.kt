package com.alacrity.randomUsers.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightThemeColors = lightColors(
    primary = primaryLight,
    primaryVariant = primaryLightVariant,
    onPrimary = Black2,
    secondary = lightSecondary,
    secondaryVariant = lightSecondaryVariant,
    onSecondary = Black2,
    error = RedErrorDark,
    onError = RedErrorLight
    )

private val DarkThemeColors = darkColors(
    primary = primaryDark,
    primaryVariant = primaryDarkVariant,
    onPrimary = White2,
    secondary = darkSecondary,
    secondaryVariant = darkSecondaryVariant,
    onSecondary = White2,
    error = RedErrorLight,
    onError = RedErrorLight
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = RandomUsersTypography,
        shapes = RandomUsersShapes,
        content = content
    )
}

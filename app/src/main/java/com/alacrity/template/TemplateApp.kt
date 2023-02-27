package com.alacrity.template

import android.content.Context
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.alacrity.template.theme.AppTheme
import com.alacrity.template.ui.main.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TemplateApp(
    context: Context,
    homeViewModel: MainViewModel
) {
    AppTheme {
            val systemUiController = rememberSystemUiController()
            val darkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
            }

            AppNavGraph(
                context = context,
                homeViewModel = homeViewModel,
            )
        }

}

//Navigation Drawer
fun customShape(screenWidth: Float, screenHeight: Float) = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(Rect(0f, 0f, screenWidth / 5, screenHeight))
    }
}


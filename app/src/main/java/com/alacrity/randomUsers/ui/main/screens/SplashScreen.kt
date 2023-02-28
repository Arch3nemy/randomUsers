package com.alacrity.randomUsers.ui.main.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.alacrity.randomUsers.R


@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        GlideImage(
            imageModel = { R.mipmap.ic_launcher },
            success = {
                it.imageBitmap?.let { bitmap ->
                    Image(
                        bitmap = bitmap,
                        contentDescription = null,
                        modifier = Modifier
                            .width(128.dp)
                            .height(128.dp)
                    )
                }
            }
        )
    }
}
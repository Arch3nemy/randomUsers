package com.alacrity.randomUsers.ui.userinfo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.alacrity.randomUsers.R
import com.alacrity.randomUsers.room.UserTableItem
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UserInfoScreen(user: UserTableItem?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
        ) {
            GlideImage(
                modifier = Modifier.fillMaxWidth(),
                imageModel = { user?.largeImage },
                success = {
                    it.imageBitmap?.let { bitmap ->
                        Image(
                            bitmap = bitmap,
                            contentDescription = stringResource(id = R.string.user_large_image),
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.2f)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                UserInfoView(text = stringResource(id = R.string.name, user?.name ?: ""))
                UserInfoView(
                    text = stringResource(
                        id = R.string.gender,
                        user?.gender ?: ""
                    )
                )
                UserInfoView(text = stringResource(id = R.string.age, user?.age ?: ""))
                UserInfoView(text = stringResource(id = R.string.email, user?.email ?: ""))
                UserInfoView(text = stringResource(id = R.string.phone, user?.phone ?: ""))
            }
        }
    }
}
@Composable
fun UserInfoView(modifier: Modifier = Modifier, text: String) {
    Text(modifier = modifier.fillMaxWidth(), text = text, textAlign = TextAlign.Center)
}



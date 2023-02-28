package com.alacrity.randomUsers.ui.main.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.History
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.alacrity.randomUsers.R
import com.alacrity.randomUsers.entity.User
import com.skydoves.landscapist.glide.GlideImage

/**
 * LocalFocusManager dismisses the keyboard when area outside keyboard is touched
 */
@Composable
fun UsersScreen(
    users: List<User>,
    onLoadUsersClick: (String) -> Unit,
    onShowHistoryClick: () -> Unit,
    onUserClick: (User) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        }
    ) {
        LazyColumn(modifier = Modifier.weight(11f)) {
            items(users) { user ->
                UserView(user = user) {
                    onUserClick(user)
                }
            }
        }
        ControlButtonsWithInputText(
            modifier = Modifier.weight(1f),
            onLoadUsersClick = { onLoadUsersClick(it); focusManager.clearFocus() },
            onShowHistoryClick = onShowHistoryClick
        )
    }
}

@Composable
fun ControlButtonsWithInputText(
    modifier: Modifier = Modifier,
    onLoadUsersClick: (String) -> Unit,
    onShowHistoryClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    var text by rememberSaveable { mutableStateOf("") }

    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

        FloatingActionButton(onClick = { onLoadUsersClick(text) }) {
            Icon(Icons.Filled.Download, stringResource(id = R.string.load_users))
        }

        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Companion.Done,
                keyboardType = KeyboardType.Number
            ),
            label = { Text(stringResource(id = R.string.amount_of_users_label)) }
        )

        FloatingActionButton(onClick = { onShowHistoryClick() }) {
            Icon(Icons.Filled.History, stringResource(id = R.string.show_history))
        }
    }
}

@Composable
fun UserView(modifier: Modifier = Modifier, user: User, onUserClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onUserClick()
            },
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            GlideImage(
                imageModel = { user.image },
                success = {
                    it.imageBitmap?.let { bitmap ->
                        Image(
                            bitmap = bitmap,
                            contentDescription = null,
                            modifier = Modifier.size(80.dp)
                        )
                    }
                }
            )

            Divider(modifier = Modifier.width(5.dp))

            Text(text = user.name)
        }
    }

}
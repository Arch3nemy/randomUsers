package com.alacrity.randomUsers.ui.userinfo

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.alacrity.randomUsers.R
import com.alacrity.randomUsers.room.UserTableItem
import com.alacrity.randomUsers.ui.userinfo.models.removeUser
import com.alacrity.randomUsers.ui.userinfo.models.saveUser
import com.alacrity.randomUsers.ui.userinfo.screens.ErrorScreen
import com.alacrity.randomUsers.ui.userinfo.screens.UserInfoScreen
import com.alacrity.randomUsers.view_states.UserInfoViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    viewModel: UserInfoViewModel,
    user: UserTableItem?,
    navController: NavController,
    allowedToSaveUsers: Boolean
) {

    val state by viewModel.viewState.collectAsState()

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    when (state) {
        is UserInfoViewState.EmptyViewState -> {
            Scaffold(topBar = {
                TopAppBar(
                    onToolbarActionClick = {
                        onToolbarActionClick(
                            navController,
                            allowedToSaveUsers,
                            user,
                            viewModel,
                            context,
                            scope
                        )
                    },
                    onBackToUsersScreen = { navController.popBackStack() },
                    allowedToSaveUsers = allowedToSaveUsers
                )
            }) { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    UserInfoScreen(user = user)
                }
            }
        }
        is UserInfoViewState.Error -> {
            ErrorScreen((state as UserInfoViewState.Error).exception)
        }
    }

    BackHandler {
        navController.popBackStack()
    }

}


/**
 * show toast after saving/removing users. Also back action after removing user
 */
private fun onToolbarActionClick(
    navController: NavController,
    allowedToSaveUsers: Boolean,
    user: UserTableItem?,
    viewModel: UserInfoViewModel,
    context: Context,
    scope: CoroutineScope
) {
    if (allowedToSaveUsers) {
        viewModel.saveUser(
            user,
            onSuccess = {
                showResultToast(
                    context,
                    scope,
                    context.getString(R.string.successfully_saved, user?.name)
                )
            },
            onFailure = {
                showResultToast(
                    context,
                    scope,
                    context.getString(R.string.already_saved)
                )
            })
    } else {
        viewModel.removeUser(
            user,
            onSuccess = {
                showResultToast(
                    context,
                    scope,
                    context.getString(R.string.successfully_removed, user?.name),
                    also = { navController.popBackStack() }
                )
            },
            onFailure = {
                showResultToast(
                    context,
                    scope,
                    context.getString(R.string.error_removing, user?.name)
                )
            })
    }
}

private fun showResultToast(
    context: Context,
    scope: CoroutineScope,
    text: String,
    also: (() -> Unit)? = null
) {
    scope.launch(Dispatchers.Main) {
        Toast.makeText(
            context,
            text,
            Toast.LENGTH_SHORT
        ).show()
        also?.invoke()
    }
}


@Composable
fun TopAppBar(
    allowedToSaveUsers: Boolean,
    onToolbarActionClick: () -> Unit,
    onBackToUsersScreen: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(R.string.userInfo)) },
        navigationIcon = {
            IconButton(onClick = { onBackToUsersScreen() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        },
        actions = {
            IconButton(onClick = { onToolbarActionClick() }) {
                Icon(
                    imageVector = if (allowedToSaveUsers) Icons.Default.Save else Icons.Default.Delete,
                    contentDescription = null
                )
            }

        }
    )
}
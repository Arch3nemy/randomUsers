package com.alacrity.randomUsers.ui.main

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.alacrity.randomUsers.ALLOWED_TO_SAVE_KEY
import com.alacrity.randomUsers.Destinations.HISTORY_ROUTE
import com.alacrity.randomUsers.Destinations.USER_INFO_ROUTE
import com.alacrity.randomUsers.USER_ARG_KEY
import com.alacrity.randomUsers.entity.User
import com.alacrity.randomUsers.extentions.toUserTableItem
import com.alacrity.randomUsers.ui.main.models.*
import com.alacrity.randomUsers.ui.main.screens.*
import com.alacrity.randomUsers.util.validateAmountString
import com.alacrity.randomUsers.view_states.MainViewState

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navController: NavController
) {

    val state by viewModel.viewState.collectAsState()

    val context = LocalContext.current

    when (state) {
        MainViewState.SplashScreen -> {
            SplashScreen()
        }
        MainViewState.Loading -> {
            LoadingScreen()
        }
        is MainViewState.UsersScreen -> {
            UsersScreen(
                users = (state as MainViewState.UsersScreen).userList,
                onLoadUsersClick = { amount ->
                    validateAmountString(context, amount) {
                        viewModel.loadUsers(it)
                    }
                },
                onShowHistoryClick = {
                    navController.navigate(HISTORY_ROUTE)
                },
                onUserClick = { user ->
                    navigateToUserInfoScreen(navController, user)
                }
            )
        }
        is MainViewState.Error -> {
            ErrorScreen((state as MainViewState.Error).exception)
        }
    }

    LaunchedEffect(key1 = state, block = {
        viewModel.enterScreen()
    })

}

private fun navigateToUserInfoScreen(navController: NavController, user: User) {
    navController.currentBackStackEntry?.savedStateHandle?.apply {
        set(key = USER_ARG_KEY, value = user.toUserTableItem())
        set(key = ALLOWED_TO_SAVE_KEY, value = true)
    }
    navController.navigate(USER_INFO_ROUTE)
}
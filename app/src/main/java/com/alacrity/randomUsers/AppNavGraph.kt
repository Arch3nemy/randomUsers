package com.alacrity.randomUsers

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alacrity.randomUsers.Destinations.HISTORY_ROUTE
import com.alacrity.randomUsers.Destinations.HOME_ROUTE
import com.alacrity.randomUsers.Destinations.USER_INFO_ROUTE
import com.alacrity.randomUsers.room.UserTableItem
import com.alacrity.randomUsers.ui.main.MainViewModel
import com.alacrity.randomUsers.ui.history.MainScreen
import com.alacrity.randomUsers.ui.history.HistoryViewModel
import com.alacrity.randomUsers.ui.userinfo.MainScreen
import com.alacrity.randomUsers.ui.userinfo.UserInfoViewModel

object Destinations {
    const val HOME_ROUTE = "home"
    const val USER_INFO_ROUTE = "user_info"
    const val HISTORY_ROUTE = "history"
}

const val USER_ARG_KEY = "arg_key"
const val ALLOWED_TO_SAVE_KEY = "allowed to save"

@Composable
fun AppNavGraph(
    mainViewModel: MainViewModel,
    historyViewModel: HistoryViewModel,
    userInfoViewModel: UserInfoViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(HOME_ROUTE) {
            com.alacrity.randomUsers.ui.main.MainScreen(
                viewModel = mainViewModel,
                navController = navController
            )
        }

        composable(USER_INFO_ROUTE) {
            val user = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<UserTableItem>(key = USER_ARG_KEY)
            val allowedToSaveUsers = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Boolean>(key = ALLOWED_TO_SAVE_KEY)

            MainScreen(
                viewModel = userInfoViewModel,
                user = user,
                navController = navController,
                allowedToSaveUsers = allowedToSaveUsers ?: false
            )
        }

        composable(HISTORY_ROUTE) {
            MainScreen(
                viewModel = historyViewModel,
                navController = navController
            )
        }

    }

}

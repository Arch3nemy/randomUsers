package com.alacrity.randomUsers

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.alacrity.randomUsers.theme.AppTheme
import com.alacrity.randomUsers.ui.main.MainViewModel
import com.alacrity.randomUsers.ui.history.HistoryViewModel
import com.alacrity.randomUsers.ui.userinfo.UserInfoViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RandomUsersApp(
    homeViewModel: MainViewModel,
    historyViewModel: HistoryViewModel,
    userInfoViewModel: UserInfoViewModel
) {
    AppTheme {
        val systemUiController = rememberSystemUiController()
        val darkIcons = MaterialTheme.colors.isLight
        SideEffect {
            systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
        }

        AppNavGraph(
            mainViewModel = homeViewModel,
            historyViewModel = historyViewModel,
            userInfoViewModel = userInfoViewModel
        )


    }

}

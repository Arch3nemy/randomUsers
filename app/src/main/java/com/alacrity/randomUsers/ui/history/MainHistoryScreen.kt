package com.alacrity.randomUsers.ui.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.alacrity.randomUsers.ALLOWED_TO_SAVE_KEY
import com.alacrity.randomUsers.Destinations
import com.alacrity.randomUsers.R
import com.alacrity.randomUsers.USER_ARG_KEY
import com.alacrity.randomUsers.entity.User
import com.alacrity.randomUsers.extentions.toUserTableItem
import com.alacrity.randomUsers.ui.history.models.enterScreen
import com.alacrity.randomUsers.ui.history.screens.HistoryScreen
import com.alacrity.randomUsers.view_states.HistoryViewState

@Composable
fun MainScreen(
    viewModel: HistoryViewModel,
    navController: NavController,
) {

    val state by viewModel.viewState.collectAsState()

    Scaffold(
        topBar = { TopAppBar { navController.popBackStack() } }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (state) {
                is HistoryViewState.FinishedLoading -> {
                    HistoryScreen(
                        users = (state as HistoryViewState.FinishedLoading).users,
                        onUserClick = { user ->
                            navigateToUserInfoScreen(navController, user)
                        }
                    )
                }
                else -> Unit
            }
        }


        LaunchedEffect(key1 = state, block = {
            viewModel.enterScreen()
        })

    }

}

private fun navigateToUserInfoScreen(navController: NavController, user: User) {
    navController.currentBackStackEntry?.savedStateHandle?.apply {
        set(key = USER_ARG_KEY, value = user.toUserTableItem())
        set(key = ALLOWED_TO_SAVE_KEY, value = false)
    }
    navController.navigate(Destinations.USER_INFO_ROUTE)
}

@Composable
fun TopAppBar(onBackToUsersScreen: () -> Unit) {
    TopAppBar(
        title = { Text(stringResource(R.string.your_history)) },
        navigationIcon = {
            IconButton(onClick = { onBackToUsersScreen() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        }
    )
}
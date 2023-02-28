package com.alacrity.randomUsers.view_states

import com.alacrity.randomUsers.entity.User


sealed class MainViewState: BaseViewState {
    object Loading : MainViewState()
    object SplashScreen : MainViewState()
    data class Error(val exception: Throwable? = null, val message: String = "") : MainViewState()
    data class UsersScreen(val userList: List<User> = emptyList()) : MainViewState()
}
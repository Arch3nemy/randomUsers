package com.alacrity.randomUsers.view_states

sealed class UserInfoViewState: BaseViewState {

    object EmptyViewState: UserInfoViewState()

    data class Error(val exception: Throwable? = null, val message: String = "") : UserInfoViewState()

}
package com.alacrity.randomUsers.view_states

import com.alacrity.randomUsers.entity.User

sealed class HistoryViewState: BaseViewState {
    object Loading : HistoryViewState()

    data class Error(val exception: Throwable? = null, val message: String = "") : HistoryViewState()

    data class FinishedLoading(val users: List<User>): HistoryViewState()
}
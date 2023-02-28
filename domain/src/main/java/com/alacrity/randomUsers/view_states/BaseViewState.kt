package com.alacrity.randomUsers.view_states

sealed interface BaseViewState {

    fun getBaseState(): BaseViewState = Loading

    companion object {
        object Loading : BaseViewState
    }
}
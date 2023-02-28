package com.alacrity.randomUsers.ui.main.models

import com.alacrity.randomUsers.BaseEvent
import com.alacrity.randomUsers.ui.main.MainViewModel

sealed class MainEvent: BaseEvent {
    object EnterScreen : MainEvent()
    data class LoadUsers(val amount: Int) : MainEvent()
}

fun MainViewModel.enterScreen() {
    obtainEvent(MainEvent.EnterScreen)
}
fun MainViewModel.loadUsers(amount: Int) {
    obtainEvent(MainEvent.LoadUsers(amount))
}

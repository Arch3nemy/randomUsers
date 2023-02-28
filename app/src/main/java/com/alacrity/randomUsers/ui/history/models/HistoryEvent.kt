package com.alacrity.randomUsers.ui.history.models

import com.alacrity.randomUsers.BaseEvent
import com.alacrity.randomUsers.ui.history.HistoryViewModel

sealed class HistoryEvent: BaseEvent {

    object EnterScreen: HistoryEvent()

}

fun HistoryViewModel.enterScreen() {
    obtainEvent(HistoryEvent.EnterScreen)
}

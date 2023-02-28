package com.alacrity.randomUsers.ui.history

import com.alacrity.randomUsers.ui.history.models.HistoryEvent
import com.alacrity.randomUsers.use_cases.GetUsersFromDatabaseUseCase
import com.alacrity.randomUsers.util.BaseViewModel
import com.alacrity.randomUsers.view_states.HistoryViewState
import com.alacrity.randomUsers.view_states.HistoryViewState.*
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val getUsersFromDatabaseUseCase: GetUsersFromDatabaseUseCase
): BaseViewModel<HistoryEvent, HistoryViewState>(Loading) {

    val viewState: StateFlow<HistoryViewState> = _viewState

    override fun obtainEvent(event: HistoryEvent) {
        when (val currentState = _viewState.value) {
            is Error -> currentState.reduce(event)
            is FinishedLoading -> currentState.reduce(event)
            is Loading -> currentState.reduce(event)
        }
    }

    private fun Loading.reduce(event: HistoryEvent) {
        logReduce(event)
        when(event) {
            is HistoryEvent.EnterScreen -> {
                loadHistory()
            }
        }
    }

    private fun FinishedLoading.reduce(event: HistoryEvent) {
        logReduce(event)
        when(event) {
            is HistoryEvent.EnterScreen -> {
                loadHistory()
            }
        }
    }

    private fun Error.reduce(event: HistoryEvent) {
        logReduce(event)
    }

    private fun loadHistory() {
        launch(
            logSuccess = "Success loading users from db",
            logError = "Error loading users from db",
            onSuccess = {
                _viewState.value = FinishedLoading(it)
            },
            onFailure = {
                _viewState.value = Error(it)
            }
        ) {
            _viewState.value = Loading
            getUsersFromDatabaseUseCase()
        }
    }
}
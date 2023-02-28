package com.alacrity.randomUsers.ui.main

import com.alacrity.randomUsers.ui.main.models.MainEvent
import com.alacrity.randomUsers.use_cases.LoadUsersUseCase
import com.alacrity.randomUsers.util.BaseViewModel
import com.alacrity.randomUsers.view_states.MainViewState
import com.alacrity.randomUsers.view_states.MainViewState.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loadUsersUseCase: LoadUsersUseCase
) : BaseViewModel<MainEvent, MainViewState>(Loading) {

    val viewState: StateFlow<MainViewState> = _viewState

    override fun obtainEvent(event: MainEvent) {
        when (val currentState = _viewState.value) {
            is Loading -> currentState.reduce(event)
            is Error -> currentState.reduce(event)
            is UsersScreen -> currentState.reduce(event)
            is SplashScreen -> currentState.reduce(event)
        }
    }

    private fun Loading.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            is MainEvent.EnterScreen -> {
                _viewState.value = SplashScreen
            }
            else -> Unit
        }
    }

    private fun SplashScreen.reduce(event: MainEvent) {
        logReduce(event)
        launch(onSuccess = {
            loadUsers(DEFAULT_USERS_AMOUNT_ON_START)
        }) {
            delay(SPLASH_SCREEN_DELAY)
        }
    }

    private fun Error.reduce(event: MainEvent) {
        logReduce(event)
    }


    private fun UsersScreen.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            is MainEvent.LoadUsers -> {
                loadUsers(event.amount)
            }
            else -> Unit
        }
    }

    private fun loadUsers(param: Int) {
        launch(
            logError = "Error loading users for param $param",
            logSuccess = "Successfully received users for $param",
            onSuccess = {
                _viewState.value = UsersScreen(it)
            },
            onFailure = {
                _viewState.value = Error(it)
            }
        ) {
            loadUsersUseCase(param)
        }
    }

    companion object {
        const val SPLASH_SCREEN_DELAY = 2000L
        const val DEFAULT_USERS_AMOUNT_ON_START = 3
    }

}
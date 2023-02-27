package com.alacrity.template.ui.main

import com.alacrity.template.ui.main.models.MainEvent
import com.alacrity.template.use_cases.GetSimpleResponseUseCase
import com.alacrity.template.util.BaseViewModel
import com.alacrity.template.view_states.MainViewState
import com.alacrity.template.view_states.MainViewState.*
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSimpleResponseUseCase: GetSimpleResponseUseCase
) : BaseViewModel<MainEvent, MainViewState>(Loading) {

    val viewState: StateFlow<MainViewState> = _viewState

    override fun obtainEvent(event: MainEvent) {
        when (val currentState = _viewState.value) {
            is Loading -> currentState.reduce(event)
            is Error -> currentState.reduce(event)
            is FinishedLoading -> currentState.reduce(event)
            is NoItems -> currentState.reduce(event)
            is Refreshing -> currentState.reduce(event)
        }
    }

    fun lal() {

    }

    private fun Loading.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.EnterScreen -> {
                getSimpleResponse(1)
            }
            else -> Unit
        }
    }

    private fun Error.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun FinishedLoading.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun NoItems.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun Refreshing.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun getSimpleResponse(param: Int) {
        launch(
            logError = "Error Getting response for param $param",
            logSuccess = "Successfully received response for param $param",
            onSuccess = {
                _viewState.value = FinishedLoading(it).also {
                    Timber.d("response title ${it.apiResponse.title}")
                }
            },
            onFailure = {
                _viewState.value = Error(it).also {
                    Timber.d("Error:: $it")
                }
            }
        ) {
            getSimpleResponseUseCase(param)
        }

    }

}
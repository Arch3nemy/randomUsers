package com.alacrity.randomUsers.ui.userinfo

import android.database.sqlite.SQLiteConstraintException
import com.alacrity.randomUsers.extentions.toUser
import com.alacrity.randomUsers.room.UserTableItem
import com.alacrity.randomUsers.ui.userinfo.models.UserInfoEvent
import com.alacrity.randomUsers.use_cases.RemoveUserFromDatabaseUseCase
import com.alacrity.randomUsers.use_cases.SaveUsersToDatabaseUseCase
import com.alacrity.randomUsers.util.BaseViewModel
import com.alacrity.randomUsers.view_states.UserInfoViewState
import com.alacrity.randomUsers.view_states.UserInfoViewState.EmptyViewState
import com.alacrity.randomUsers.view_states.UserInfoViewState.Error
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UserInfoViewModel @Inject constructor(
    private val saveUserToDatabaseUseCase: SaveUsersToDatabaseUseCase,
    private val removeUserFromDatabaseUseCase: RemoveUserFromDatabaseUseCase
) : BaseViewModel<UserInfoEvent, UserInfoViewState>(EmptyViewState) {

    val viewState: StateFlow<UserInfoViewState> = _viewState

    override fun obtainEvent(event: UserInfoEvent) {
        when (val currentState = _viewState.value) {
            is EmptyViewState -> currentState.reduce(event)
            is Error -> currentState.reduce(event)
        }
    }

    private fun EmptyViewState.reduce(event: UserInfoEvent) {
        logReduce(event)
        when (event) {
            is UserInfoEvent.SaveUser -> {
                event.user?.let { saveUserToDatabase(it, event.onSuccess, event.onFailure) }
            }
            is UserInfoEvent.RemoveUser -> {
                event.user?.let { removeUserFromDatabase(it, event.onSuccess, event.onFailure) }
            }
        }
    }

    private fun Error.reduce(event: UserInfoEvent) {
        logReduce(event)
    }

    /**
     * SqliteConstraintException probably means that user is already added to database
     */
    private fun saveUserToDatabase(tableItem: UserTableItem, onSuccess: () -> Unit, onFailure: (Throwable) -> Unit) {
        launch(
            logSuccess = "Successfully saved user ${tableItem.name} to database",
            logError = "Error saving user ${tableItem.name} to database",
            onSuccess = { onSuccess() },
            onFailure = { if(it is SQLiteConstraintException) onFailure(it) else _viewState.value = Error(it) }
        ) {
            saveUserToDatabaseUseCase(tableItem.toUser())
        }
    }

    private fun removeUserFromDatabase(tableItem: UserTableItem, onSuccess: () -> Unit, onFailure: (Throwable) -> Unit) {
        launch(
            logSuccess = "Successfully removed user ${tableItem.name} from database",
            logError = "Error removing user ${tableItem.name} from database",
            onSuccess = { onSuccess() },
            onFailure = { _viewState.value = Error(it); onFailure(it) }
        ) {
            removeUserFromDatabaseUseCase(tableItem.toUser())
        }
    }


}


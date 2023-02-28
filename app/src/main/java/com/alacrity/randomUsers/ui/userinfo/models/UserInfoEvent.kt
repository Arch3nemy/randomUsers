package com.alacrity.randomUsers.ui.userinfo.models

import com.alacrity.randomUsers.BaseEvent
import com.alacrity.randomUsers.room.UserTableItem
import com.alacrity.randomUsers.ui.userinfo.UserInfoViewModel

sealed class UserInfoEvent: BaseEvent {

    data class SaveUser(val user: UserTableItem?, val onSuccess: () -> Unit, val onFailure: (Throwable) -> Unit): UserInfoEvent()

    data class RemoveUser(val user: UserTableItem?, val onSuccess: () -> Unit, val onFailure: (Throwable) -> Unit): UserInfoEvent()

}

fun UserInfoViewModel.saveUser(user: UserTableItem?, onSuccess: () -> Unit, onFailure:(Throwable) -> Unit) {
    obtainEvent(UserInfoEvent.SaveUser(user, onSuccess, onFailure))
}

fun UserInfoViewModel.removeUser(user: UserTableItem?, onSuccess: () -> Unit, onFailure:(Throwable) -> Unit) {
    obtainEvent(UserInfoEvent.RemoveUser(user, onSuccess, onFailure))
}
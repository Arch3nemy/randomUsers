package com.alacrity.randomUsers.extentions

import com.alacrity.randomUsers.entity.User
import com.alacrity.randomUsers.room.UserTableItem

fun User.toUserTableItem(): UserTableItem = let {
    UserTableItem(it.name, it.image, it.largeImage, it.age, it.email, it.phone, it.gender)
}

fun UserTableItem.toUser(): User = let {
    User(it.name, it.image, it.largeImage, it.age, it.email, it.phone, it.gender)
}
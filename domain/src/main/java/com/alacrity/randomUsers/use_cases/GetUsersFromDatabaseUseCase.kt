package com.alacrity.randomUsers.use_cases

import com.alacrity.randomUsers.entity.User

interface GetUsersFromDatabaseUseCase {

    suspend operator fun invoke(): List<User>

}
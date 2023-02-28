package com.alacrity.randomUsers.use_cases

import com.alacrity.randomUsers.entity.User

interface LoadUsersUseCase {

    suspend operator fun invoke(number: Int): List<User>

}
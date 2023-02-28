package com.alacrity.randomUsers.use_cases

import com.alacrity.randomUsers.entity.User

interface SaveUsersToDatabaseUseCase {

    suspend operator fun invoke(user: User)

}
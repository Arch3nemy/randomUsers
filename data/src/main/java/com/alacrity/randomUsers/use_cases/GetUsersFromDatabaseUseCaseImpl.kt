package com.alacrity.randomUsers.use_cases

import com.alacrity.randomUsers.entity.User
import com.alacrity.randomUsers.repository.Repository
import javax.inject.Inject

class GetUsersFromDatabaseUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetUsersFromDatabaseUseCase {

    override suspend fun invoke(): List<User> = repository.getAllUsersFromDatabase()


}
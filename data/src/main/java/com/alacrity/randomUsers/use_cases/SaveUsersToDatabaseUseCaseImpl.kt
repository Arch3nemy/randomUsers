package com.alacrity.randomUsers.use_cases

import com.alacrity.randomUsers.entity.User
import com.alacrity.randomUsers.repository.Repository
import javax.inject.Inject

class SaveUsersToDatabaseUseCaseImpl @Inject constructor(
    private val repository: Repository
): SaveUsersToDatabaseUseCase {

    override suspend fun invoke(user: User) = repository.saveUserToDatabase(user)

}
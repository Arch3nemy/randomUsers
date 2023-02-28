package com.alacrity.randomUsers.use_cases

import com.alacrity.randomUsers.repository.Repository
import javax.inject.Inject

class LoadUsersUseCaseImpl @Inject constructor(
    private val repository: Repository
): LoadUsersUseCase {

    override suspend fun invoke(number: Int) = repository.loadUsersFromApi(number)

}
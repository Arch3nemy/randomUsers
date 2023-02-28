package com.alacrity.randomUsers.repository

import com.alacrity.randomUsers.entity.User

interface Repository {

    suspend fun loadUsersFromApi(number: Int): List<User>
    suspend fun getAllUsersFromDatabase(): List<User>
    suspend fun removeUserFromDatabase(user: User)
    suspend fun saveUserToDatabase(user: User)

}
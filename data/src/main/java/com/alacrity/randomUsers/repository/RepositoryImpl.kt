package com.alacrity.randomUsers.repository

import com.alacrity.randomUsers.api.Api
import com.alacrity.randomUsers.entity.User
import com.alacrity.randomUsers.exceptions.RandomUsersException
import com.alacrity.randomUsers.retrofit.NetworkResponse
import com.alacrity.randomUsers.room.AppDatabase
import com.alacrity.randomUsers.room.UserTableItem
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val api: Api
) : Repository {

    override suspend fun loadUsersFromApi(number: Int): List<User> {
        when (val call = api.loadUsersWithAmount(number)) {
            is NetworkResponse.Success -> {
                val response = call.body
                return response.results.map {
                    User(
                        name ="${it.name.first} ${it.name.last}",
                        image = it.picture.medium,
                        largeImage = it.picture.large,
                        age = it.dob.age,
                        email = it.email,
                        phone = it.phone,
                        gender = it.gender
                    )
                }
            }
            is NetworkResponse.ApiError -> {
                throw RandomUsersException("Api error")
            }
            is NetworkResponse.NetworkError -> {
                throw RandomUsersException("Network error")
            }
            is NetworkResponse.UnknownError -> {
                throw RandomUsersException("Unknown error")
            }
        }
    }

    override suspend fun getAllUsersFromDatabase(): List<User> = db.userDao()
        .getAll()
        .map {
            User(
                it.name,
                it.image,
                it.largeImage,
                it.age,
                it.email,
                it.phone,
                it.gender
            )
        }

    override suspend fun removeUserFromDatabase(user: User) = db.userDao().delete(user.let {
        UserTableItem(
            it.name,
            it.image,
            it.largeImage,
            it.age,
            it.email,
            it.phone,
            it.gender
        )
    })

    override suspend fun saveUserToDatabase(user: User) = db.userDao().insertAll(user.let {
        UserTableItem(
            it.name,
            it.image,
            it.largeImage,
            it.age,
            it.email,
            it.phone,
            it.gender
        )
    })

}
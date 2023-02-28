package com.alacrity.randomUsers.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

        @Query("SELECT * FROM users")
        fun getAll(): List<UserTableItem>

        @Insert
        fun insertAll(users: UserTableItem)

        @Delete
        fun delete(user: UserTableItem)

}
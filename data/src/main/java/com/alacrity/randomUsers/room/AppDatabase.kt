package com.alacrity.randomUsers.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserTableItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
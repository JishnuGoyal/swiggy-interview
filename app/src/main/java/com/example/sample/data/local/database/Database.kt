package com.example.sample.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sample.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class Database(): RoomDatabase() {
    abstract val dao: DatabaseDao
}
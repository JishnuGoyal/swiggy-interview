package com.example.sample.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sample.data.local.database.Database
import com.example.sample.data.local.database.DatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            "name_of_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: Database): DatabaseDao {
        return db.dao
    }

}
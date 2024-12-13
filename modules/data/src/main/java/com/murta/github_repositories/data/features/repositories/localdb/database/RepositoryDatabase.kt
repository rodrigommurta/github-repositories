package com.murta.github_repositories.data.features.repositories.localdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.murta.github_repositories.data.features.repositories.localdb.dao.RepositoryDao
import com.murta.github_repositories.data.features.repositories.localdb.entities.RepositoryEntity
import com.murta.github_repositories.data.features.user.localdb.entities.UserTypeConverters

@Database(
    entities = [ RepositoryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UserTypeConverters::class)
abstract class RepositoryDatabase : RoomDatabase() {
    abstract val dao: RepositoryDao

    companion object {
        @Volatile
        private var INSTANCE: RepositoryDatabase? = null

        fun getInstance(context: Context): RepositoryDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RepositoryDatabase::class.java,
                        "repository_cache_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
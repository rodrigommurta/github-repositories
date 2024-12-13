package com.murta.github_repositories.data.features.user.localdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.murta.github_repositories.data.features.user.localdb.dao.UserDao
import com.murta.github_repositories.data.features.user.localdb.entities.UserEntity
import com.murta.github_repositories.data.features.user.localdb.entities.UserTypeConverters

@Database(
    entities = [ UserEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UserTypeConverters::class)
abstract class UserDatabase : RoomDatabase() {
    abstract val dao: UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_cache_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
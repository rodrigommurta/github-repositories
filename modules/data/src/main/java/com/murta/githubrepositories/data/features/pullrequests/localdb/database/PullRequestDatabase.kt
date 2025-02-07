package com.murta.githubrepositories.data.features.pullrequests.localdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.murta.githubrepositories.data.features.pullrequests.localdb.dao.PullRequestDao
import com.murta.githubrepositories.data.features.pullrequests.localdb.entities.PullRequestEntity
import com.murta.githubrepositories.data.features.user.localdb.entities.UserTypeConverters

@Database(
    entities = [ PullRequestEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(UserTypeConverters::class)
abstract class PullRequestDatabase : RoomDatabase() {
    abstract val dao: PullRequestDao

    companion object {
        @Volatile
        private var INSTANCE: PullRequestDatabase? = null

        fun getInstance(context: Context): PullRequestDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PullRequestDatabase::class.java,
                        "pullrequest_cache_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
package com.murta.github_repositories.data.di

import android.content.Context
import com.murta.github_repositories.data.features.pullrequests.localdb.dao.PullRequestDao
import com.murta.github_repositories.data.features.pullrequests.localdb.database.PullRequestDatabase
import com.murta.github_repositories.data.features.repositories.localdb.dao.RepositoryDao
import com.murta.github_repositories.data.features.repositories.localdb.database.RepositoryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesPullRequestsDatabase(context: Context): PullRequestDao {
        return PullRequestDatabase.getInstance(context).dao
    }

    @Singleton
    @Provides
    fun providesRepositoriesDatabase(context: Context): RepositoryDao {
        return RepositoryDatabase.getInstance(context).dao
    }
}
package com.murta.githubrepositories.data.di

import com.murta.githubrepositories.data.features.pullrequests.PullRequestsProviderImpl
import com.murta.githubrepositories.data.features.pullrequests.PullRequestsService
import com.murta.githubrepositories.data.features.pullrequests.localdb.dao.PullRequestDao
import com.murta.githubrepositories.data.features.repositories.RepositoriesProviderImpl
import com.murta.githubrepositories.data.features.repositories.RepositoriesService
import com.murta.githubrepositories.data.features.repositories.localdb.dao.RepositoryDao
import com.murta.githubrepositories.domain.features.pullrequests.PullRequestsProvider
import com.murta.githubrepositories.domain.features.repositories.RepositoriesProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProviderModule {

    @Singleton
    @Provides
    fun providesRepositoriesProvider(
        service: RepositoriesService,
        dao: RepositoryDao
    ): RepositoriesProvider {
        return RepositoriesProviderImpl(
            service = service,
            dao = dao,
        )
    }

    @Singleton
    @Provides
    fun providesPullRequestsProvider(
        service: PullRequestsService,
        dao: PullRequestDao
    ): PullRequestsProvider {
        return PullRequestsProviderImpl(
            service = service,
            dao = dao,
        )
    }
}
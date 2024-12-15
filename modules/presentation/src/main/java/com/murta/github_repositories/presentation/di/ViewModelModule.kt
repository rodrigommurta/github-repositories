package com.murta.github_repositories.presentation.di

import com.murta.github_repositories.presentation.ui.features.pullrequests.PullRequestsViewModel
import com.murta.github_repositories.presentation.ui.features.repositories.RepositoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [ViewModelFactoryModule::class]
)
interface ViewModelModule {

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(RepositoriesViewModel::class)]
    fun bindsRepositoriesViewModelFactory(factory: RepositoriesViewModel.Factory): ViewModelAssistedFactory<*>

    @Binds
    @[IntoMap ViewModelAssistedFactoryKey(PullRequestsViewModel::class)]
    fun bindsPullRequestsViewModelFactory(factory: PullRequestsViewModel.Factory): ViewModelAssistedFactory<*>
}
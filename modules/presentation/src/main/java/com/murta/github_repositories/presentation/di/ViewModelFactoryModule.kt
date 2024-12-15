package com.murta.github_repositories.presentation.di

import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindsDaggerViewModelAssistedFactory(factory: GenericViewModelAssistedFactory): ViewModelFactory
}
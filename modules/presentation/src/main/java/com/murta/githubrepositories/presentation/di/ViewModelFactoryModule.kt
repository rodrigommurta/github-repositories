package com.murta.githubrepositories.presentation.di

import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindsDaggerViewModelAssistedFactory(factory: GenericViewModelAssistedFactory): ViewModelFactory
}
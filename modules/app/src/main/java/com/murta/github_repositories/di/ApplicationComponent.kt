package com.murta.github_repositories.di

import android.content.Context
import com.murta.github_repositories.data.di.DatabaseModule
import com.murta.github_repositories.data.di.NetworkModule
import com.murta.github_repositories.data.di.ProviderModule
import com.murta.github_repositories.presentation.di.ViewModelFactory
import com.murta.github_repositories.presentation.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class,
        ProviderModule::class,
        ViewModelModule::class,
    ]
)
interface ApplicationComponent {
    fun getViewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}
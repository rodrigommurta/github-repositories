package com.murta.githubrepositories.di

import android.content.Context
import com.murta.githubrepositories.data.di.DatabaseModule
import com.murta.githubrepositories.data.di.NetworkModule
import com.murta.githubrepositories.data.di.ProviderModule
import com.murta.githubrepositories.presentation.di.ViewModelFactory
import com.murta.githubrepositories.presentation.di.ViewModelModule
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
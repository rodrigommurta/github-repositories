package com.murta.github_repositories

import android.app.Application
import com.murta.github_repositories.di.ApplicationComponent
import com.murta.github_repositories.di.DaggerApplicationComponent


class GitHubRepositoriesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }

    companion object {
        private lateinit var applicationComponent: ApplicationComponent
        val app: ApplicationComponent get() = applicationComponent
    }
}
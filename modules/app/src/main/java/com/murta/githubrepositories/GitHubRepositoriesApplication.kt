package com.murta.githubrepositories

import android.app.Application
import com.murta.githubrepositories.di.ApplicationComponent
import com.murta.githubrepositories.di.DaggerApplicationComponent


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
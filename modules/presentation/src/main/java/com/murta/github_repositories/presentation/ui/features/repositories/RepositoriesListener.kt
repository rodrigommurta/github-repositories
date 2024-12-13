package com.murta.github_repositories.presentation.ui.features.repositories

import com.murta.github_repositories.domain.features.repositories.model.Repository
import com.murta.github_repositories.presentation.ui.utils.FeedbackListener

interface RepositoriesListener: FeedbackListener {
    fun onRepositoryClicked(repository: Repository)
}

object DummyRepositoriesListener: RepositoriesListener {
    override fun onRepositoryClicked(repository: Repository) = Unit
    override fun onButtonClicked() = Unit
}
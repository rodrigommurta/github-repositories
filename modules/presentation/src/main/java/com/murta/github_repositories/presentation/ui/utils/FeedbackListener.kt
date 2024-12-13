package com.murta.github_repositories.presentation.ui.utils

interface FeedbackListener {
    fun onButtonClicked()
}

object DummyFeedbackListener: FeedbackListener {
    override fun onButtonClicked() = Unit
}
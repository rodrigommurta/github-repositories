package com.murta.github_repositories.domain.features.pullrequests

import com.murta.github_repositories.domain.features.pullrequests.model.PullRequest
import com.murta.github_repositories.domain.utils.State
import kotlinx.coroutines.flow.Flow

interface PullRequestsProvider {
    suspend fun getScreen(param: String): Flow<State<List<PullRequest>>>
}
package com.murta.githubrepositories.domain.features.pullrequests

import com.murta.githubrepositories.domain.features.pullrequests.model.PullRequest
import com.murta.githubrepositories.domain.utils.State
import kotlinx.coroutines.flow.Flow

interface PullRequestsProvider {
    suspend fun getScreen(param: String): Flow<State<List<PullRequest>>>
}
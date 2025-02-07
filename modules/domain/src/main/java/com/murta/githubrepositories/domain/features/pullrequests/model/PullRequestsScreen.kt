package com.murta.githubrepositories.domain.features.pullrequests.model

import com.murta.githubrepositories.domain.utils.State
import com.murta.githubrepositories.domain.utils.StateBearer
import java.io.Serializable

data class PullRequestsScreen(
    override val state: State<Any> = State.Loading(),
    val title: String = "",
    val pullRequests: List<PullRequest>? = null,
): Serializable, StateBearer

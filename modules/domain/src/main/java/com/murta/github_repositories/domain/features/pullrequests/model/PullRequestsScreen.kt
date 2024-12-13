package com.murta.github_repositories.domain.features.pullrequests.model

import com.murta.github_repositories.domain.utils.State
import com.murta.github_repositories.domain.utils.StateBearer
import java.io.Serializable

data class PullRequestsScreen(
    override val state: State<Any> = State.Loading(),
    val title: String = "",
    val pullRequests: List<PullRequest>? = null,
): Serializable, StateBearer

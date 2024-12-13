package com.murta.github_repositories.domain.features.pullrequests.model

import com.murta.github_repositories.domain.features.user.User
import java.io.Serializable

data class PullRequest(
    val id: Int,
    val url: String,
    val state: String,
    val title: String,
    val body: String,
    val date: String,
    val user: User,
) : Serializable

package com.murta.githubrepositories.domain.features.pullrequests.model

import com.murta.githubrepositories.domain.features.user.User
import java.io.Serializable

data class PullRequest(
    val id: Long,
    val url: String,
    val state: String,
    val title: String,
    val body: String?,
    val date: String,
    val user: User,
) : Serializable

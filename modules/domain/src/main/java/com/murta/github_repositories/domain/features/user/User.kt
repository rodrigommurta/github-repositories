package com.murta.github_repositories.domain.features.user

import java.io.Serializable

data class User(
    val id: Int,
    val url: String,
    val name: String,
    val avatarUrl: String,
) : Serializable

package com.murta.github_repositories.domain.features.repositories.model

import com.murta.github_repositories.domain.features.user.User
import java.io.Serializable

data class Repository(
    val id: Int,
    val repositoryUrl: String,
    val pullsUrl:String,
    val name: String,
    val description: String,
    val starsCount: Int,
    val forksCount: Int,
    val owner: User,
): Serializable

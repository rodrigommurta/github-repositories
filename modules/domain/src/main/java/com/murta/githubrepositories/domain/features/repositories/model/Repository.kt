package com.murta.githubrepositories.domain.features.repositories.model

import com.murta.githubrepositories.domain.features.user.User
import java.io.Serializable

data class Items(
    val items: List<Repository>,
): Serializable

data class Repository(
    val id: Int,
    val repositoryUrl: String,
    val pullsUrl:String,
    val name: String,
    val fullName: String,
    val description: String,
    val starsCount: Int,
    val forksCount: Int,
    val owner: User,
): Serializable

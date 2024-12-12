package com.murta.github_repositories.data.features.repositories.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.murta.github_repositories.data.features.common.UserRemote
import com.murta.github_repositories.data.features.common.toDomain
import com.murta.github_repositories.domain.features.repositories.model.Repository
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("url") val repositoryUrl: String,
    @SerializedName("pulls_url") val pullsUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("stargazers_count") val starsCount: Int,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("owner") val owner: UserRemote,
) : Parcelable

fun RepositoryRemote.toDomain() = Repository(
    id = id,
    repositoryUrl = repositoryUrl,
    pullsUrl = pullsUrl,
    name = name,
    description = description,
    starsCount = starsCount,
    forksCount = forksCount,
    owner = owner.toDomain(),
)
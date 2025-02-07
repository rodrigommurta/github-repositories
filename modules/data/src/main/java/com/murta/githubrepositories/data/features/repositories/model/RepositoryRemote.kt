package com.murta.githubrepositories.data.features.repositories.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.murta.githubrepositories.data.features.repositories.localdb.entities.RepositoryEntity
import com.murta.githubrepositories.data.features.user.UserRemote
import com.murta.githubrepositories.data.features.user.toDb
import com.murta.githubrepositories.data.features.user.toDomain
import com.murta.githubrepositories.domain.features.repositories.model.Repository
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemsRemote(
    @SerializedName("items") val items: List<RepositoryRemote>,
) : Parcelable

@Parcelize
data class RepositoryRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("url") val repositoryUrl: String,
    @SerializedName("pulls_url") val pullsUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
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
    fullName = fullName,
    description = description,
    starsCount = starsCount,
    forksCount = forksCount,
    owner = owner.toDomain(),
)

fun RepositoryRemote.toDb() = RepositoryEntity(
    id = id,
    repositoryUrl = repositoryUrl,
    pullsUrl = pullsUrl,
    name = name,
    fullName = fullName,
    description = description,
    starsCount = starsCount,
    forksCount = forksCount,
    owner = owner.toDb(),
)

fun List<RepositoryRemote>.toDb() = this.map {
    it.toDb()
}
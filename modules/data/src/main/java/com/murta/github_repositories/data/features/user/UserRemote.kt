package com.murta.github_repositories.data.features.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.murta.github_repositories.data.features.user.localdb.entities.UserEntity
import com.murta.github_repositories.domain.features.user.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("html_url") val url: String,
    @SerializedName("login") val name: String,
    @SerializedName("avatar_url") val avatarUrl: String,
) : Parcelable

fun UserRemote.toDomain() = User(
    id = id,
    url = url,
    name = name,
    avatarUrl = avatarUrl,
)

fun UserRemote.toDb() = UserEntity(
    id = id,
    url = url,
    name = name,
    avatarUrl = avatarUrl,
)
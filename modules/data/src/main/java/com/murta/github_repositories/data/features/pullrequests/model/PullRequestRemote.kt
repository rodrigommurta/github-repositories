package com.murta.github_repositories.data.features.pullrequests.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.murta.github_repositories.data.features.pullrequests.localdb.entities.PullRequestEntity
import com.murta.github_repositories.data.features.user.UserRemote
import com.murta.github_repositories.data.features.user.toDb
import com.murta.github_repositories.data.features.user.toDomain
import com.murta.github_repositories.domain.features.pullrequests.model.PullRequest
import kotlinx.parcelize.Parcelize

@Parcelize
data class PullRequestRemote(
    @SerializedName("id") val id: Long,
    @SerializedName("html_url") val url: String,
    @SerializedName("state") val state: String,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String?,
    @SerializedName("created_at") val date: String,
    @SerializedName("user") val user: UserRemote,
) : Parcelable

fun PullRequestRemote.toDomain() = PullRequest(
    id = id,
    url = url,
    state = state,
    title = title,
    body = body,
    date = date,
    user = user.toDomain(),
)

fun PullRequestRemote.toDb() = PullRequestEntity(
    id = id,
    url = url,
    state = state,
    title = title,
    body = body,
    date = date,
    user = user.toDb(),
)

fun List<PullRequestRemote>.toDb() = this.map {
    it.toDb()
}
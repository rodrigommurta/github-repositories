package com.murta.github_repositories.data.features.pullrequests.localdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.murta.github_repositories.data.features.user.localdb.entities.UserEntity
import com.murta.github_repositories.data.features.user.localdb.entities.toDomain
import com.murta.github_repositories.domain.features.pullrequests.model.PullRequest

@Entity(tableName = "pullRequest")
data class PullRequestEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "html_url") val url: String,
    @ColumnInfo(name = "state") val state: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String?,
    @ColumnInfo(name = "created_at") val date: String,
    @ColumnInfo(name = "user") val user: UserEntity,
)

fun PullRequestEntity.toDomain() = PullRequest(
    id = id,
    url = url,
    state = state,
    title = title,
    body = body,
    date = date,
    user = user.toDomain(),
)

fun List<PullRequestEntity>.toDomain() = this.map {
    it.toDomain()
}
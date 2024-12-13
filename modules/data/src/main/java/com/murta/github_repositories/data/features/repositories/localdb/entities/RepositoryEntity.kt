package com.murta.github_repositories.data.features.repositories.localdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.murta.github_repositories.data.features.user.localdb.entities.UserEntity
import com.murta.github_repositories.data.features.user.localdb.entities.toDomain
import com.murta.github_repositories.domain.features.repositories.model.Repository

@Entity(tableName = "repository")
data class RepositoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "url") val repositoryUrl: String,
    @ColumnInfo(name = "pulls_url") val pullsUrl: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "stargazers_count") val starsCount: Int,
    @ColumnInfo(name = "forks_count") val forksCount: Int,
    @ColumnInfo(name = "owner") val owner: UserEntity,
)

fun RepositoryEntity.toDomain() = Repository(
    id = id,
    repositoryUrl = repositoryUrl,
    pullsUrl = pullsUrl,
    name = name,
    description = description,
    starsCount = starsCount,
    forksCount = forksCount,
    owner = owner.toDomain(),
)

fun List<RepositoryEntity>.toDomain() = this.map {
    it.toDomain()
}
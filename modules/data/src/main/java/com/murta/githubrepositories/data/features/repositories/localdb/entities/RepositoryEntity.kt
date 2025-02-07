package com.murta.githubrepositories.data.features.repositories.localdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.murta.githubrepositories.data.features.user.localdb.entities.UserEntity
import com.murta.githubrepositories.data.features.user.localdb.entities.toDomain
import com.murta.githubrepositories.domain.features.repositories.model.Repository

@Entity(tableName = "repository")
data class RepositoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "url") val repositoryUrl: String,
    @ColumnInfo(name = "pulls_url") val pullsUrl: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "full_name") val fullName: String,
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
    fullName = fullName,
    description = description,
    starsCount = starsCount,
    forksCount = forksCount,
    owner = owner.toDomain(),
)

fun List<RepositoryEntity>.toDomain() = this.map {
    it.toDomain()
}
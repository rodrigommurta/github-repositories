package com.murta.githubrepositories.data.features.user.localdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.murta.githubrepositories.domain.features.user.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "login") val name: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String,
)

fun UserEntity.toDomain() = User(
    id = id,
    url = url,
    name = name,
    avatarUrl = avatarUrl,
)

class UserTypeConverters {
    @TypeConverter
    fun fromUser(value: UserEntity): String = Gson().toJson(value)

    @TypeConverter
    fun toUser(value: String): UserEntity = Gson().fromJson(value, UserEntity::class.java)
}
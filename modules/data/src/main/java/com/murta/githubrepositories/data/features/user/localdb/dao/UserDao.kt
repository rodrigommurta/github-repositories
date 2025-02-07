package com.murta.githubrepositories.data.features.user.localdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.murta.githubrepositories.data.features.user.localdb.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: UserEntity)

    @Query("SELECT * FROM user")
    fun queryUser(): Flow<UserEntity>

    @Query("DELETE FROM user")
    suspend fun delete()
}
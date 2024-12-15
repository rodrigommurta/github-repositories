package com.murta.github_repositories.data.features.repositories.localdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.murta.github_repositories.data.features.repositories.localdb.entities.RepositoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<RepositoryEntity>)

    @Query("SELECT * FROM repository ORDER BY stargazers_count DESC")
    fun queryRepositories(): Flow<List<RepositoryEntity>>

    @Query("DELETE FROM repository")
    suspend fun deleteAll()
}
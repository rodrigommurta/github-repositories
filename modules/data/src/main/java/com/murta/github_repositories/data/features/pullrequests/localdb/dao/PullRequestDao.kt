package com.murta.github_repositories.data.features.pullrequests.localdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.murta.github_repositories.data.features.pullrequests.localdb.entities.PullRequestEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PullRequestDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<PullRequestEntity>)

    @Query("SELECT * FROM pullRequest")
    fun queryPullRequests(): Flow<List<PullRequestEntity>>

    @Query("DELETE FROM pullRequest")
    suspend fun deleteAll()
}
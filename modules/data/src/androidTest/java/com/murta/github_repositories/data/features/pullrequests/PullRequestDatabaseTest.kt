package com.murta.github_repositories.data.features.pullrequests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.murta.github_repositories.data.features.pullrequests.localdb.dao.PullRequestDao
import com.murta.github_repositories.data.features.pullrequests.localdb.database.PullRequestDatabase
import com.murta.github_repositories.data.features.pullrequests.localdb.entities.PullRequestEntity
import com.murta.github_repositories.data.features.user.localdb.entities.UserEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PullRequestDatabaseTest {
    private lateinit var dbPullRequests: List<PullRequestEntity>
    private lateinit var dao: PullRequestDao
    private lateinit var pullRequestDatabase: PullRequestDatabase

    @Before
    fun createInstances() {
        val pullRequest = PullRequestEntity(
            id = 1,
            url = "https://api.github.com/repos/JetBrains/kotlin/pulls/5386",
            state = "open",
            title = "Skip restoring this when generating spill and unspill for coroutines",
            body = "As described in the [associated YouTrack issue](https://youtrack.jetbrains.com/issue/KT-73328/why-this-local-variable-spilling-is-needed-in-Kotlin-Coroutines)，there seems to be no need to restore this variable for suspend instance method.\\r\\n",
            date = "2024-12-10T12:41:32Z",
            user = UserEntity(
                id = 7991360,
                url = "https://api.github.com/users/tripleCC",
                name = "tripleCC",
                avatarUrl = "https://avatars.githubusercontent.com/u/7991360?v=4"
            )
        )

        dbPullRequests = List(3) { index ->
            pullRequest.copy(
                id = (index + 1).toLong(),
                user = pullRequest.user.copy(
                    id = index + 1
                )
            )
        }
    }

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        pullRequestDatabase = Room.inMemoryDatabaseBuilder(
            context,
            PullRequestDatabase::class.java
        ).build()

        dao = pullRequestDatabase.dao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        pullRequestDatabase.close()
    }

    @Test
    fun should_write_in_database() {
        lateinit var result: List<PullRequestEntity>

        runBlocking {
            result = dao.queryPullRequests().first()
        }
        assertTrue(result.isEmpty())

        runBlocking {
            dao.insertAll(dbPullRequests)
            result = dao.queryPullRequests().first()
        }
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun should_read_from_database() {
        lateinit var result: PullRequestEntity

        runBlocking {
            dao.deleteAll()

            dao.insertAll(dbPullRequests.subList(0, 1))
            result = dao.queryPullRequests().first()[0]
        }

        assertEquals(result.id, dbPullRequests[0].id)
    }

    @Test
    fun should_clear_database() {
        lateinit var result: List<PullRequestEntity>

        runBlocking {
            dao.insertAll(dbPullRequests)
            dao.deleteAll()
            result = dao.queryPullRequests().first()
        }
        assertTrue(result.isEmpty())
    }
}
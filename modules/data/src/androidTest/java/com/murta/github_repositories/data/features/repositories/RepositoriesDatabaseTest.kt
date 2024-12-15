package com.murta.github_repositories.data.features.repositories

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.murta.github_repositories.data.features.repositories.localdb.dao.RepositoryDao
import com.murta.github_repositories.data.features.repositories.localdb.database.RepositoryDatabase
import com.murta.github_repositories.data.features.repositories.localdb.entities.RepositoryEntity
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
class RepositoriesDatabaseTest {
    private lateinit var dbRepositories: List<RepositoryEntity>
    private lateinit var dao: RepositoryDao
    private lateinit var repositoryDatabase: RepositoryDatabase

    @Before
    fun createInstances() {
        val repository = RepositoryEntity(
            id = 1,
            repositoryUrl = "https://api.github.com/repos/JetBrains/kotlin",
            pullsUrl = "https://api.github.com/repos/JetBrains/kotlin/pulls",
            name = "kotlin",
            fullName = "JetBrains/kotlin",
            description = "The Kotlin Programming Language. ",
            starsCount = 49565,
            forksCount = 5797,
            owner = UserEntity(
                id = 878437,
                url = "https://api.github.com/users/JetBrains",
                name = "JetBrains",
                avatarUrl = "https://avatars.githubusercontent.com/u/878437?v=4"
            )
        )

        dbRepositories = List(3) { index ->
            repository.copy(
                id = index + 1,
                owner = repository.owner.copy(
                    id = index + 1
                )
            )
        }
    }

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        repositoryDatabase = Room.inMemoryDatabaseBuilder(
            context,
            RepositoryDatabase::class.java
        ).build()

        dao = repositoryDatabase.dao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        repositoryDatabase.close()
    }

    @Test
    fun should_write_in_database() {
        lateinit var result: List<RepositoryEntity>

        runBlocking {
            result = dao.queryRepositories().first()
        }
        assertTrue(result.isEmpty())

        runBlocking {
            dao.insertAll(dbRepositories)
            result = dao.queryRepositories().first()
        }
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun should_read_from_database() {
        lateinit var result: RepositoryEntity

        runBlocking {
            dao.deleteAll()

            dao.insertAll(dbRepositories.subList(0, 1))
            result = dao.queryRepositories().first()[0]
        }

        assertEquals(result.id, dbRepositories[0].id)
    }

    @Test
    fun should_clear_database() {
        lateinit var result: List<RepositoryEntity>

        runBlocking {
            dao.insertAll(dbRepositories)
            dao.deleteAll()
            result = dao.queryRepositories().first()
        }
        assertTrue(result.isEmpty())
    }
}
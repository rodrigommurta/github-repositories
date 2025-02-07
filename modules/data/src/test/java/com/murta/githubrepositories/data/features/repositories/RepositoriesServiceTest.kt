package com.murta.githubrepositories.data.features.repositories

import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class RepositoriesServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: RepositoriesService

    private val mapParams = mapOf(
        "q" to "language:Kotlin",
        "sort" to "stars",
        "page" to "1",
    )

    @Before
    fun createService() {
        val factory = GsonBuilder().create()

        mockWebServer = MockWebServer().apply(MockWebServer::start)
        service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(factory))
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(RepositoriesService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `SHOULD hit right path with expected endpoint`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody(REPOSITORIES))
            val result = service.getRepositories(mapParams)
            val request = mockWebServer.takeRequest()

            assertEquals(request.path, "/search/repositories?q=language%3AKotlin&sort=stars&page=1")
        }
    }

    @Test
    fun `Verify if PullRequests was parsed successfully`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody(REPOSITORIES))
            val result = service.getRepositories(mapParams)

            with(result) {
                assertEquals(items.size, 2)

                with(items.first()) {
                    assertEquals(id, 3432266)
                    assertEquals(repositoryUrl, "https://api.github.com/repos/JetBrains/kotlin")
                    assertEquals(
                        pullsUrl,
                        "https://api.github.com/repos/JetBrains/kotlin/pulls{/number}"
                    )
                    assertEquals(name, "kotlin")
                    assertEquals(fullName, "JetBrains/kotlin")
                    assertEquals(description, "The Kotlin Programming Language. ")
                    assertEquals(starsCount, 49585)
                    assertEquals(forksCount, 5799)
                    with(owner) {
                        assertEquals(id, 878437)
                        assertEquals(url, "https://github.com/JetBrains")
                        assertEquals(name, "JetBrains")
                        assertEquals(
                            avatarUrl,
                            "https://avatars.githubusercontent.com/u/878437?v=4"
                        )
                    }
                }

                with(items.last()) {
                    assertEquals(id, 5152285)
                    assertEquals(repositoryUrl, "https://api.github.com/repos/square/okhttp")
                    assertEquals(
                        pullsUrl,
                        "https://api.github.com/repos/square/okhttp/pulls{/number}"
                    )
                    assertEquals(name, "okhttp")
                    assertEquals(fullName, "square/okhttp")
                    assertEquals(
                        description,
                        "Square’s meticulous HTTP client for the JVM, Android, and GraalVM."
                    )
                    assertEquals(starsCount, 45973)
                    assertEquals(forksCount, 9171)
                    with(owner) {
                        assertEquals(id, 82592)
                        assertEquals(url, "https://github.com/square")
                        assertEquals(name, "square")
                        assertEquals(avatarUrl, "https://avatars.githubusercontent.com/u/82592?v=4")
                    }
                }
            }
        }
    }
}
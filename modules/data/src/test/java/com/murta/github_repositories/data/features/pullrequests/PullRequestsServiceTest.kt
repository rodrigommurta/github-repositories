package com.murta.github_repositories.data.features.pullrequests

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
class PullRequestsServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: PullRequestsService

    @Before
    fun createService() {
        val factory = GsonBuilder().create()

        mockWebServer = MockWebServer().apply(MockWebServer::start)
        service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(factory))
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(PullRequestsService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `SHOULD hit right path with expected endpoint`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody(PULL_REQUESTS))
            val result = service.getPullRequests("JetBrains/kotlin")
            val request = mockWebServer.takeRequest()

            assertEquals(request.path, "/repos/JetBrains/kotlin/pulls")
        }
    }

    @Test
    fun `Verify if PullRequests was parsed successfully`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody(PULL_REQUESTS))
            val result = service.getPullRequests("JetBrains/kotlin")

            with(result) {
                assertEquals(size, 2)

                with(first()) {
                    assertEquals(id, 2235339165)
                    assertEquals(url, "https://github.com/JetBrains/kotlin/pull/5388")
                    assertEquals(state, "open")
                    assertEquals(title, "Buffer CompileEnvironmentUtil.writeToJar")
                    assertEquals(
                        body,
                        "The JAR format has a lot of single int writes. Without buffering, these are very inefficient.\r\n\r\nCloses https://youtrack.jetbrains.com/issue/KT-73922"
                    )
                    assertEquals(date, "2024-12-14T19:22:58Z")
                    with(user) {
                        assertEquals(id, 40366972)
                        assertEquals(url, "https://github.com/nreid260")
                        assertEquals(name, "nreid260")
                        assertEquals(
                            avatarUrl,
                            "https://avatars.githubusercontent.com/u/40366972?v=4"
                        )
                    }
                }

                with(last()) {
                    assertEquals(id, 2234599694)
                    assertEquals(url, "https://github.com/JetBrains/kotlin/pull/5387")
                    assertEquals(state, "open")
                    assertEquals(
                        title,
                        "Register LibraryFilterCachingService without using rootProject reference"
                    )
                    assertEquals(
                        body,
                        "LibraryFilterCachingService shared service was being registered through rootProject. That breaks Gradle Isolated Projects (see KT-54105)\r\n\r\nInstead, register through the current project, as shared services are shared through the entire build."
                    )
                    assertEquals(date, "2024-12-13T21:50:32Z")
                    with(user) {
                        assertEquals(id, 1418185)
                        assertEquals(url, "https://github.com/liutikas")
                        assertEquals(name, "liutikas")
                        assertEquals(
                            avatarUrl,
                            "https://avatars.githubusercontent.com/u/1418185?v=4"
                        )
                    }
                }
            }
        }
    }
}
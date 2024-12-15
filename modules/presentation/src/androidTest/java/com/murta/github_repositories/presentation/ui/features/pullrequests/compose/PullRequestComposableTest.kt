package com.murta.github_repositories.presentation.ui.features.pullrequests.compose

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.murta.github_repositories.domain.features.pullrequests.model.PullRequest
import com.murta.github_repositories.domain.features.user.User
import org.junit.Rule
import org.junit.Test

class PullRequestComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pullRequestComposableTest() {
        composeTestRule.setContent {
            PullRequestComposable(
                modifier = Modifier,
                pullRequest = pullRequest,
            )
        }

        composeTestRule.onNodeWithTag(TITLE_TAG)
            .assertIsDisplayed()
            .assertTextEquals(pullRequest.title)

        composeTestRule.onNodeWithTag(BODY_TAG)
            .assertIsDisplayed()
            .assertTextEquals(pullRequest.body!!)

        composeTestRule.onNodeWithTag(AVATAR_TAG)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(USER_NAME_TAG)
            .assertIsDisplayed()
            .assertTextEquals(pullRequest.user.name)
    }

    @Test
    fun pullRequestWithoutBodyComposableTest() {
        composeTestRule.setContent {
            PullRequestComposable(
                modifier = Modifier,
                pullRequest = pullRequest.copy(body = null),
            )
        }

        composeTestRule.onNodeWithTag(BODY_TAG)
            .assertIsNotDisplayed()
    }

    companion object {
        private val pullRequest = PullRequest(
            id = 1218763,
            url = "https://api.github.com/repos/JetBrains/kotlin/pulls/5386",
            state = "open",
            title = "Skip restoring this when generating spill and unspill for coroutines",
            body = "As described in the [associated YouTrack issue](https://youtrack.jetbrains.com/issue/KT-73328/why-this-local-variable-spilling-is-needed-in-Kotlin-Coroutines)，there seems to be no need to restore this variable for suspend instance method.\\r\\n",
            date = "2024-12-10T12:41:32Z",
            user = User(
                id = 7991360,
                url = "https://api.github.com/users/tripleCC",
                name = "tripleCC",
                avatarUrl = "https://avatars.githubusercontent.com/u/7991360?v=4"
            )
        )
    }
}
package com.murta.githubrepositories.presentation.ui.features.repositories.compose

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.murta.githubrepositories.domain.features.repositories.model.Repository
import com.murta.githubrepositories.domain.features.user.User
import org.junit.Rule
import org.junit.Test

class RepositoryComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun repositoryComposableTest() {
        composeTestRule.setContent {
            RepositoryComposable(
                modifier = Modifier,
                repository = repository
            )
        }

        composeTestRule.onNodeWithTag(TITLE_TAG)
            .assertIsDisplayed()
            .assertTextEquals(repository.name)

        composeTestRule.onNodeWithTag(DESCRIPTION_TAG)
            .assertIsDisplayed()
            .assertTextEquals(repository.description)

        composeTestRule.onNodeWithTag(FORKS_TAG)
            .assertIsDisplayed()
            .assertTextEquals(repository.forksCount.toString())

        composeTestRule.onNodeWithTag(STARS_TAG)
            .assertIsDisplayed()
            .assertTextEquals(repository.starsCount.toString())

        composeTestRule.onNodeWithTag(AVATAR_TAG)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(OWNER_NAME_TAG)
            .assertIsDisplayed()
            .assertTextEquals(repository.owner.name)
    }

    companion object {
        private val repository = Repository(
            id = 131656,
            repositoryUrl = "https://api.github.com/repos/JetBrains/kotlin",
            pullsUrl = "https://api.github.com/repos/JetBrains/kotlin/pulls",
            name = "kotlin",
            fullName = "JetBrains/kotlin",
            description = "The Kotlin Programming Language. ",
            starsCount = 49565,
            forksCount = 5797,
            owner = User(
                id = 878437,
                url = "https://api.github.com/users/JetBrains",
                name = "JetBrains",
                avatarUrl = "https://avatars.githubusercontent.com/u/878437?v=4"
            )
        )
    }
}
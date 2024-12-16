package com.murta.github_repositories.presentation.ui.utils.compose

import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import com.murta.github_repositories.domain.utils.ErrorInformation
import com.murta.github_repositories.domain.utils.State
import org.junit.Rule
import org.junit.Test

private const val SCREEN_TAG = "screenTag"

class BaseScreenComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingBaseScreen() {
        composeTestRule.setContent {
            BaseScreenComposable(
                modifier = Modifier.testTag(SCREEN_TAG),
                screenState = State.Loading(),
                hasCache = false,
                onFeedbackButtonClicked = {},
                topBar = {},
                errorContent = {},
            ) {
                Text("Success content")
            }
        }

        composeTestRule.onNodeWithTag(SCREEN_TAG)
            .onChildren()
            .assertAny(hasTestTag(LOADING_TAG))
            .assertAny(hasTestTag(ERROR_TAG).not())
            .assertAny(hasTestTag(SUCCESS_TAG).not())
    }

    @Test
    fun errorBaseScreen() {
        composeTestRule.setContent {
            BaseScreenComposable(
                modifier = Modifier.testTag(SCREEN_TAG),
                screenState = State.Error(
                    error = ErrorInformation()
                ),
                hasCache = false,
                onFeedbackButtonClicked = {},
                topBar = {},
                errorContent = {},
            ) {
                Text("Success content")
            }
        }

        composeTestRule.onNodeWithTag(SCREEN_TAG)
            .onChildren()
            .assertAny(hasTestTag(LOADING_TAG).not())
            .assertAny(hasTestTag(ERROR_TAG))
            .assertAny(hasTestTag(SUCCESS_TAG).not())
    }

    @Test
    fun successBaseScreen() {
        composeTestRule.setContent {
            BaseScreenComposable(
                modifier = Modifier.testTag(SCREEN_TAG),
                screenState = State.Success(
                    data = "Success data"
                ),
                hasCache = false,
                onFeedbackButtonClicked = {},
                topBar = {},
                errorContent = {},
            ) {
                Text("Success content")
            }
        }

        composeTestRule.onNodeWithTag(SCREEN_TAG)
            .onChildren()
            .assertAny(hasTestTag(LOADING_TAG).not())
            .assertAny(hasTestTag(ERROR_TAG).not())
            .assertAny(hasTestTag(SUCCESS_TAG))
    }
}
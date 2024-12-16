package com.murta.github_repositories.presentation.ui.utils.compose

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

private const val ERROR_FEEDBACK_TAG = "errorFeedbackTag"

class ErrorFeedbackComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorFeedbackComposableTest() {
        composeTestRule.setContent {
            ErrorFeedbackComposable(
                modifier = Modifier.testTag(ERROR_FEEDBACK_TAG),
                onFeedbackButtonClicked = {},
            )
        }

        composeTestRule.onNodeWithTag(ERROR_FEEDBACK_TAG)
            .onChildren()
            .assertAny((hasTestTag(ERROR_TEXT_TAG)))
            .assertAny((hasTestTag(ERROR_BUTTON_TAG)))
        
        composeTestRule.onNodeWithTag(ERROR_BUTTON_TAG)
            .assertHasClickAction()
    }
}
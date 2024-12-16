package com.murta.github_repositories.presentation.ui.utils.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.murta.github_repositories.domain.utils.State

const val LOADING_TAG = "loadingTag"
const val ERROR_TAG = "errorTag"
const val SUCCESS_TAG = "successTag"

@Composable
fun BaseScreenComposable(
    modifier: Modifier = Modifier,
    screenState: State<Any> = State.Loading(),
    hasCache: Boolean = false,
    onFeedbackButtonClicked: () -> Unit,
    topBar: @Composable () -> Unit,
    errorContent: @Composable () -> Unit = {
        ErrorFeedbackComposable(
            modifier = modifier,
            errorMessage = screenState.error?.message.orEmpty(),
            onFeedbackButtonClicked = { onFeedbackButtonClicked() }
        )
    },
    loadingContent: @Composable () -> Unit = {
        CircularProgressIndicator(
            modifier = Modifier
                .size(48.dp)
                .padding(vertical = 24.dp),
            color = Color.Blue
        )
    },
    successContent: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            topBar()
        },
    ) { innerPadding ->
        if (screenState.isLoading && !hasCache) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .testTag(LOADING_TAG),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.weight(1f))
                loadingContent()
                Spacer(modifier = Modifier.weight(1f))
            }
        } else if (screenState.isError && !hasCache) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .testTag(ERROR_TAG),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                errorContent()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .testTag(SUCCESS_TAG),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                successContent()
            }
        }
    }
}
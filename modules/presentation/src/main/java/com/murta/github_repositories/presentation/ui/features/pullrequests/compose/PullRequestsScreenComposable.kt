package com.murta.github_repositories.presentation.ui.features.pullrequests.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.murta.github_repositories.domain.features.pullrequests.model.PullRequestsScreen
import com.murta.github_repositories.domain.features.repositories.model.RepositoriesScreen
import com.murta.github_repositories.domain.utils.State
import com.murta.github_repositories.presentation.ui.features.pullrequests.PullRequestsListener
import com.murta.github_repositories.presentation.ui.features.pullrequests.compose.fakedata.PullRequestsScreenComposableFakeData
import com.murta.github_repositories.presentation.ui.features.repositories.DummyRepositoriesListener
import com.murta.github_repositories.presentation.ui.features.repositories.RepositoriesListener
import com.murta.github_repositories.presentation.ui.features.repositories.compose.RepositoryComposable
import com.murta.github_repositories.presentation.ui.features.repositories.compose.fakedata.RepositoriesScreenComposableFakeData
import com.murta.github_repositories.presentation.ui.utils.compose.ErrorFeedbackComposable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PullRequestsScreenComposable(
    modifier: Modifier = Modifier,
    screen: PullRequestsScreen = PullRequestsScreen(),
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        modifier = Modifier,
                        text = screen.title,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
                    containerColor = Color.Blue
                )
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            val errorWithoutCache = screen.state.isError && screen.pullRequests.isNullOrEmpty()

            if (errorWithoutCache) {
                val state = screen.state as State.Error
                ErrorFeedbackComposable(
                    modifier = modifier,
//                    .testTag(ERROR_TAG),
                    errorMessage = state.error.message.orEmpty(),
//                    listener = listener,
                )
            } else if (screen.state is State.Loading) {
                Column(modifier = modifier) {
                    Spacer(modifier = Modifier.weight(1f))

                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.CenterHorizontally),
//                        .testTag(LOADING_IMAGE_TAG),
                        color = Color.Blue
                    )

                    Spacer(modifier = Modifier.weight(1f))
                }

            } else {
                screen.pullRequests?.let { pullRequests ->
                    Column(
                        modifier = modifier
                            .horizontalScroll(rememberScrollState())
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))

                        pullRequests.forEachIndexed { index, pullRequest ->
                            PullRequestComposable(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        TODO()
                                    },
                                pullRequest = pullRequest,
                            )

                            if (index < pullRequests.lastIndex) {
                                Spacer(modifier = Modifier.height(16.dp))
                                HorizontalDivider()
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview("PullRequestsScreenPreview", showBackground = true)
fun PullRequestsScreenComposablePreview(
    @PreviewParameter(PullRequestsScreenComposableFakeData::class) screen: PullRequestsScreen
) {
    PullRequestsScreenComposable(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        screen = screen,
    )
}
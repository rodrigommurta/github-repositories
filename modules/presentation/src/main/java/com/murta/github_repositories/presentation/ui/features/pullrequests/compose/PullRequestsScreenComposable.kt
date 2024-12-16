package com.murta.github_repositories.presentation.ui.features.pullrequests.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.murta.github_repositories.domain.features.pullrequests.model.PullRequestsScreen
import com.murta.github_repositories.domain.utils.State
import com.murta.github_repositories.presentation.ui.features.pullrequests.compose.fakedata.PullRequestsScreenComposableFakeData
import com.murta.github_repositories.presentation.ui.utils.compose.BaseScreenComposable
import com.murta.github_repositories.presentation.ui.utils.compose.EndlessListComposable
import com.murta.presentation.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PullRequestsScreenComposable(
    modifier: Modifier = Modifier,
    screen: PullRequestsScreen = PullRequestsScreen(),
    onPullRequestClicked: (url: String) -> Unit,
    onBottomReached: () -> Unit,
    onFeedbackButtonClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
) {
    val hasCache = screen.pullRequests != null

    BaseScreenComposable(
        modifier = Modifier.fillMaxSize(),
        screenState = screen.state,
        hasCache = hasCache,
        onFeedbackButtonClicked = onFeedbackButtonClicked,
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
                    containerColor = Color.DarkGray
                ),
                navigationIcon = {
                    IconButton(
                        onClick = onBackButtonClicked
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.icon_arrow_back_description)
                        )
                    }
                }
            )
        }
    ) {
        Column {
            screen.pullRequests?.let { pullRequests ->
                if (pullRequests.isEmpty()) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        text = "Não há pull requests disponíveis para este repositório",
                        fontSize = 20.sp,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                EndlessListComposable(
                    modifier = modifier,
                    list = pullRequests,
                    onBottomReached = onBottomReached,
                    content = { index ->
                        val item = pullRequests[index]

                        PullRequestComposable(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .clickable {
                                    onPullRequestClicked(item.url)
                                },
                            pullRequest = item,
                        )

                        if (index < pullRequests.lastIndex) {
                            HorizontalDivider()
                        } else if (screen.state is State.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(48.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .padding(vertical = 24.dp),
                                color = Color.Blue
                            )
                        }
                    }
                )
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
        onPullRequestClicked = {},
        onBottomReached = {},
        onFeedbackButtonClicked = {},
        onBackButtonClicked = {}
    )
}
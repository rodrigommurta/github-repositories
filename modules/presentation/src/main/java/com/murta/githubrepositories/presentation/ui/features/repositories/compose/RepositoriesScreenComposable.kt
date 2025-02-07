package com.murta.githubrepositories.presentation.ui.features.repositories.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import com.murta.githubrepositories.domain.features.repositories.model.RepositoriesScreen
import com.murta.githubrepositories.domain.utils.State
import com.murta.githubrepositories.presentation.ui.features.repositories.compose.fakedata.RepositoriesScreenComposableFakeData
import com.murta.githubrepositories.presentation.ui.navigation.PullRequestsRoute
import com.murta.githubrepositories.presentation.ui.utils.compose.BaseScreenComposable
import com.murta.githubrepositories.presentation.ui.utils.compose.EndlessListComposable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RepositoriesScreenComposable(
    modifier: Modifier = Modifier,
    screen: RepositoriesScreen = RepositoriesScreen(),
    onRepositoryClicked: (PullRequestsRoute) -> Unit,
    onBottomReached: () -> Unit,
    onFeedbackButtonClicked: () -> Unit,
) {
    val hasCache = !screen.repositories.isNullOrEmpty()

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
                )
            )
        }
    ) {
        Column {
            screen.repositories?.let { repositories ->
                EndlessListComposable(
                    modifier = modifier,
                    list = repositories,
                    onBottomReached = onBottomReached,
                    content = { index ->
                        val item = repositories[index]

                        RepositoryComposable(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .clickable {
                                    onRepositoryClicked(
                                        PullRequestsRoute(
                                            title = item.name,
                                            fullName = item.fullName
                                        )
                                    )
                                },
                            repository = item,
                        )

                        if (index < repositories.lastIndex) {
                            HorizontalDivider()
                        } else if (screen.state is State.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(48.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .padding(vertical = 12.dp),
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
@Preview("RepositoriesScreenPreview", showBackground = true)
fun RepositoriesScreenComposablePreview(
    @PreviewParameter(RepositoriesScreenComposableFakeData::class) screen: RepositoriesScreen
) {
    RepositoriesScreenComposable(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        screen = screen,
        onRepositoryClicked = {},
        onBottomReached = {},
        onFeedbackButtonClicked = {},
    )
}
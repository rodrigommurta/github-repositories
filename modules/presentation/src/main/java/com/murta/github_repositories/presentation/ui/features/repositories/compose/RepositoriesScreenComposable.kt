package com.murta.github_repositories.presentation.ui.features.repositories.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.murta.github_repositories.domain.features.repositories.model.RepositoriesScreen
import com.murta.github_repositories.domain.utils.State
import com.murta.github_repositories.presentation.ui.features.repositories.compose.fakedata.RepositoriesScreenComposableFakeData
import com.murta.github_repositories.presentation.ui.navigation.PullRequestsRoute
import com.murta.github_repositories.presentation.ui.utils.compose.ErrorFeedbackComposable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RepositoriesScreenComposable(
    modifier: Modifier = Modifier,
    screen: RepositoriesScreen = RepositoriesScreen(),
    onRepositoryClicked: (PullRequestsRoute) -> Unit,
//    listener: RepositoriesListener
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
            val errorWithoutCache = screen.state.isError && screen.repositories.isNullOrEmpty()

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
                screen.repositories?.let { repositories ->
                    Column(
                        modifier = modifier
                            .verticalScroll(rememberScrollState())
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))

                        repositories.forEachIndexed { index, repository ->
                            RepositoryComposable(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
//                                        listener.onRepositoryClicked(repository)
                                        onRepositoryClicked(
                                            PullRequestsRoute(
                                                title = repository.name,
                                                pullsUrl = repository.pullsUrl
                                            )
                                        )
                                    },
                                repository = repository,
                            )

                            if (index < repositories.lastIndex) {
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
@Preview("RepositoriesScreenPreview", showBackground = true)
fun RepositoriesScreenComposablePreview(
    @PreviewParameter(RepositoriesScreenComposableFakeData::class) screen: RepositoriesScreen
) {
    RepositoriesScreenComposable(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        screen = screen,
        onRepositoryClicked = {}
//        listener = DummyRepositoriesListener
    )
}
package com.murta.github_repositories.presentation.ui.features.pullrequests.compose.fakedata

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.murta.github_repositories.domain.features.pullrequests.model.PullRequest

class PullRequestComposableFakeData : PreviewParameterProvider<PullRequest> {
    override val values: Sequence<PullRequest>
        get() = sequenceOf(
            pullRequestFakeData(1)
        )
}
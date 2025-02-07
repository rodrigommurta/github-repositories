package com.murta.githubrepositories.presentation.ui.features.repositories.compose.fakedata

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.murta.githubrepositories.domain.features.repositories.model.Repository

class RepositoryComposableFakeData : PreviewParameterProvider<Repository> {
    override val values: Sequence<Repository>
        get() = sequenceOf(
            repositoryFakeData(1)
        )
}
package com.murta.github_repositories.presentation.ui.features.pullrequests.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.RoundedCornersTransformation
import com.murta.github_repositories.domain.features.pullrequests.model.PullRequest
import com.murta.github_repositories.presentation.ui.features.pullrequests.compose.fakedata.PullRequestComposableFakeData
import com.murta.presentation.R

@Composable
fun PullRequestComposable(
    modifier: Modifier = Modifier,
    pullRequest: PullRequest,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            modifier = Modifier,
            text = pullRequest.title,
            color = Color.Blue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier,
            text = pullRequest.body,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(8.dp))


        Row {
            AsyncImage(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
//                    .testTag(IMAGE_TAG),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pullRequest.user.avatarUrl)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .transformations(RoundedCornersTransformation(16f))
                    .build(),
                placeholder = painterResource(R.drawable.ic_avatar_placeholder),
                contentDescription = stringResource(R.string.image_profile_description),
                contentScale = ContentScale.Crop,
            )

            Text(
                modifier = Modifier,
                text = pullRequest.user.name,
                color = Color.Blue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
@Preview("PullRequestComposablePreview", showBackground = true)
private fun PullRequestComposablePreview(
    @PreviewParameter(PullRequestComposableFakeData::class) pullRequest: PullRequest
) {
    PullRequestComposable(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        pullRequest = pullRequest,
    )
}
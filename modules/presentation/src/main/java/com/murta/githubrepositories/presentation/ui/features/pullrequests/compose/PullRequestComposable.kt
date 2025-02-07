package com.murta.githubrepositories.presentation.ui.features.pullrequests.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.RoundedCornersTransformation
import com.murta.githubrepositories.domain.features.pullrequests.model.PullRequest
import com.murta.githubrepositories.presentation.ui.features.pullrequests.compose.fakedata.PullRequestComposableFakeData
import com.murta.presentation.R

const val TITLE_TAG = "titleTag"
const val BODY_TAG = "bodyTag"
const val AVATAR_TAG = "avatarTag"
const val USER_NAME_TAG = "userNameTag"

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
            modifier = Modifier.testTag(TITLE_TAG),
            text = pullRequest.title,
            color = Color.Blue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        pullRequest.body?.let {
            Text(
                modifier = Modifier.testTag(BODY_TAG),
                text = it,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .testTag(AVATAR_TAG),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pullRequest.user.avatarUrl)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .transformations(RoundedCornersTransformation(radius = 16f))
                    .build(),
                placeholder = painterResource(R.drawable.ic_avatar_placeholder),
                contentDescription = stringResource(R.string.image_profile_description),
                contentScale = ContentScale.Crop,
            )

            Text(
                modifier = Modifier.testTag(USER_NAME_TAG),
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
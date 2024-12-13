package com.murta.github_repositories.presentation.ui.features.repositories.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.murta.github_repositories.domain.features.repositories.model.Repository
import com.murta.github_repositories.presentation.ui.features.repositories.compose.fakedata.RepositoryComposableFakeData
import com.murta.presentation.R

@Composable
fun RepositoryComposable(
    modifier: Modifier = Modifier,
    repository: Repository,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                modifier = Modifier,
                text = repository.name,
                color = Color.Blue,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier,
                text = repository.description,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Icon(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    painter = painterResource(R.drawable.ic_git_fork),
                    contentDescription = stringResource(R.string.icon_fork_description),
                    tint = Color.Yellow,
                )
                Text(
                    modifier = Modifier,
                    text = repository.forksCount.toString(),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    painter = painterResource(R.drawable.ic_git_star),
                    contentDescription = stringResource(R.string.icon_star_description),
                    tint = Color.Yellow,
                )

                Text(
                    modifier = Modifier,
                    text = repository.starsCount.toString(),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape),
//                    .testTag(IMAGE_TAG),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(repository.owner.avatarUrl)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .transformations(RoundedCornersTransformation(16f))
                    .build(),
                placeholder = painterResource(R.drawable.ic_avatar_placeholder),
                contentDescription = stringResource(R.string.image_profile_description),
                contentScale = ContentScale.Crop,
            )

            Text(
                modifier = Modifier,
                text = repository.owner.name,
                color = Color.Blue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
@Preview("RepositoryComposablePreview", showBackground = true)
private fun RepositoryComposablePreview(
    @PreviewParameter(RepositoryComposableFakeData::class) repository: Repository
) {
    RepositoryComposable(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        repository = repository,
    )
}
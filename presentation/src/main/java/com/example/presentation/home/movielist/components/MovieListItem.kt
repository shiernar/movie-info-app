package com.example.presentation.home.movielist.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.common.ui.theme.AppTheme
import com.example.common.ui.theme.dimensions
import com.example.presentation.R
import com.example.presentation.home.movielist.viewobjects.MovieVO


@Composable
fun MovieListItem(modifier: Modifier = Modifier,
                  movieVO: MovieVO,
                  onMovieItemClicked: (movieId : Int) -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = MaterialTheme.dimensions.shadowElevationSmall, clip = true)
            .padding(
                horizontal = MaterialTheme.dimensions.paddingMedium,
                vertical = MaterialTheme.dimensions.paddingMediumLarge
            )
            .clickable { onMovieItemClicked(movieVO.id) }
            .padding(MaterialTheme.dimensions.paddingSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.paddingMedium)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieVO.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.movie_list_item_content_description),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(MaterialTheme.dimensions.photoMedium)
                .aspectRatio(11f / 16f, true)
                .clip(RoundedCornerShape(MaterialTheme.dimensions.roundedCornerSmall)),
            error = painterResource(id = R.drawable.no_image_error),
            placeholder = painterResource(id = R.drawable.image_place_holder)
        )
        Column(modifier = Modifier.align(Alignment.Top)) {
            Text(text = movieVO.name, style = MaterialTheme.typography.titleMedium)
            movieVO.releaseDate?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED, showBackground = true)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
fun MovieListItemPreview(){
   AppTheme {
        Surface {
            MovieListItem(
                movieVO = MovieVO(
                    id = 2756,
                    name = "Mad Max",
                    releaseDate = "Août 2024",
                    imageUrl = "https://image.movieglu.com/2756/GBR_002756h0.jpg"
                ),
                onMovieItemClicked = {}
            )
        }
    }
}
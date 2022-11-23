package ro.alexmamo.firemag.components.icons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ThumbImage(
    url: String,
    width: Dp,
    height: Dp
) {
    AsyncImage(
        modifier = Modifier
            .width(width)
            .height(height),
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null
    )
}
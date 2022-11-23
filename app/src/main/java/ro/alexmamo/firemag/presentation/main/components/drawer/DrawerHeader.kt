package ro.alexmamo.firemag.presentation.main.components.drawer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ro.alexmamo.firemag.domain.model.User

@Composable
fun DrawerHeader(
    user: User
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .width(48.dp)
                .height(48.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.photoUrl)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Column(
            modifier = Modifier.padding(start = 8.dp),
        ) {
            Text(
                text = user.displayName,
                fontSize = 24.sp,
                color = Color.LightGray
            )
            Text(
                text = user.email,
                fontSize = 12.sp,
                color = Color.LightGray
            )
        }
    }
}
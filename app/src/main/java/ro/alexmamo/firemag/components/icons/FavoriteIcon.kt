package ro.alexmamo.firemag.components.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import ro.alexmamo.firemag.R

@Composable
fun FavoriteIcon(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Outlined.Favorite,
            tint = colorResource(id = R.color.accent),
            contentDescription = null
        )
    }
}
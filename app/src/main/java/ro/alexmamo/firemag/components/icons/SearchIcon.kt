package ro.alexmamo.firemag.components.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun SearchIcon(
    onSearchIconClick: () -> Unit
) {
    IconButton(
        onClick = onSearchIconClick
    ) {
        Icon(
            Icons.Filled.Search,
            contentDescription = null
        )
    }
}
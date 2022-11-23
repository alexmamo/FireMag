package ro.alexmamo.firemag.components.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun NavigationIcon(
    openNavigationDrawer: () -> Unit
) {
    IconButton(
        onClick = openNavigationDrawer
    ) {
        Icon(
            imageVector = Icons.Outlined.Menu,
            contentDescription = null,
        )
    }
}
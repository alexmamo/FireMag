package ro.alexmamo.firemag.presentation.main.components.drawer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DrawerDivider() {
    Divider(
        modifier = Modifier.padding(
            start = 8.dp,
            end = 8.dp,
            bottom = 16.dp
        ),
        color = Color.LightGray
    )
}
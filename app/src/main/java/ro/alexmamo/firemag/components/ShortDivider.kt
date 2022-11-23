package ro.alexmamo.firemag.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShortDivider() {
    Divider(
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp
        ),
        color = Color.LightGray,
        thickness = 3.dp
    )
}
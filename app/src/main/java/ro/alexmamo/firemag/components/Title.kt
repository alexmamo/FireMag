package ro.alexmamo.firemag.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Title(
    text: String
) {
    Text(
        modifier = Modifier.padding(12.dp),
        text = text,
        fontSize = 18.sp,
        color = Color.DarkGray
    )
}
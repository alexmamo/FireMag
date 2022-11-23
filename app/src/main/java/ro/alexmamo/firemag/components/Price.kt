package ro.alexmamo.firemag.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import ro.alexmamo.firemag.R
import ro.alexmamo.firemag.core.AppConstants.DOT
import ro.alexmamo.firemag.core.AppConstants.US_DOLLAR

@Composable
fun Price(
    price: String,
    fontSize: TextUnit
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = colorResource(R.color.accent),
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(US_DOLLAR)
            }
            withStyle(
                style = SpanStyle(
                    color = Color.DarkGray
                )
            ) {
                append(price.addCharAtIndex(price.length - 2))
            }
        },
        fontSize = fontSize,
        maxLines = 1
    )
}
fun String.addCharAtIndex(index: Int) = StringBuilder(this).apply {
    insert(index, DOT)
}.toString()
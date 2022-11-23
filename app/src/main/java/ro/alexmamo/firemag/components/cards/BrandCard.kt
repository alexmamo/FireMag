package ro.alexmamo.firemag.components.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ro.alexmamo.firemag.components.icons.ThumbImage
import ro.alexmamo.firemag.core.AppConstants.NO_VALUE
import ro.alexmamo.firemag.core.Utils.Companion.getImageUrl
import ro.alexmamo.firemag.domain.model.Brand
import ro.alexmamo.firemag.domain.model.Image.BrandImage

@Composable
@ExperimentalMaterial3Api
fun BrandCard(
    brand: Brand,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(
            start = 4.dp,
            end = 4.dp
        ),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onClick
    ) {
        val url = getImageUrl(
            image = BrandImage,
            name = brand.name ?: NO_VALUE,
            token = brand.token ?: NO_VALUE
        )
        ThumbImage(
            url = url,
            width = 64.dp,
            height = 64.dp
        )
    }
}
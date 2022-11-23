package ro.alexmamo.firemag.components.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ro.alexmamo.firemag.core.AppConstants.NO_VALUE
import ro.alexmamo.firemag.core.Utils.Companion.getImageUrl
import ro.alexmamo.firemag.domain.model.Banner
import ro.alexmamo.firemag.domain.model.Image.BannerImage

@Composable
@ExperimentalMaterial3Api
fun BannerCard(
    banner: Banner,
    navigateToProductDetailsScreen: (productId: String) -> Unit,
) {
    val productId = banner.productId ?: NO_VALUE
    val token = banner.token ?: NO_VALUE

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {
            navigateToProductDetailsScreen(productId)
        }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    getImageUrl(
                        image = BannerImage,
                        name = productId,
                        token = token
                    )
                )
                .crossfade(true)
                .build(),
            contentDescription = null
        )
    }
}
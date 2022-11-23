package ro.alexmamo.firemag.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.alexmamo.firemag.components.Price
import ro.alexmamo.firemag.components.icons.ThumbImage
import ro.alexmamo.firemag.core.AppConstants.NO_VALUE
import ro.alexmamo.firemag.core.Utils.Companion.getImageUrl
import ro.alexmamo.firemag.domain.model.Image.ProductThumbImage
import ro.alexmamo.firemag.domain.model.Product

@Composable
@ExperimentalMaterial3Api
fun ProductCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.padding(4.dp).width(172.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onClick
    ) {
        Column {
            product.apply {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    val url = getImageUrl(
                        image = ProductThumbImage,
                        name = id ?: NO_VALUE,
                        token = thumb ?: NO_VALUE
                    )
                    ThumbImage(
                        url = url,
                        width = 128.dp,
                        height = 128.dp
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    name?.let { productName ->
                        Text(
                            text = productName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                        Price(
                            price = price.toString(),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}
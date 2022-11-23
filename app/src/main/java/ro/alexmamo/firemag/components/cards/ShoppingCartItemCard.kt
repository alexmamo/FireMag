package ro.alexmamo.firemag.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.alexmamo.firemag.R
import ro.alexmamo.firemag.components.Price
import ro.alexmamo.firemag.components.icons.ThumbImage
import ro.alexmamo.firemag.core.AppConstants.MINUS
import ro.alexmamo.firemag.core.AppConstants.NO_VALUE
import ro.alexmamo.firemag.core.AppConstants.PLUS
import ro.alexmamo.firemag.core.Utils.Companion.getImageUrl
import ro.alexmamo.firemag.domain.model.Image.ProductThumbImage
import ro.alexmamo.firemag.domain.model.ShoppingCartItem

@Composable
@ExperimentalMaterial3Api
fun ShoppingCartItemCard(
    item: ShoppingCartItem,
    incrementQuantity: (itemId: String) -> Unit,
    decrementQuantity: (itemId: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(
                start = 8.dp,
                end = 8.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item.apply {
                val url = getImageUrl(
                    image = ProductThumbImage,
                    name = id ?: NO_VALUE,
                    token = thumb ?: NO_VALUE
                )
                ThumbImage(
                    url = url,
                    width = 64.dp,
                    height = 64.dp
                )
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically).padding(start = 8.dp)
                ) {
                    Text(
                        text = name ?: NO_VALUE
                    )
                    Price(
                        price = price.toString(),
                        fontSize = 12.sp
                    )
                }
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Text(
                    modifier = Modifier.padding(8.dp).clickable {
                        item.id?.let { itemId ->
                            decrementQuantity(itemId)
                        }
                    },
                    fontSize = 21.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold,
                    text = MINUS
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    fontSize = 21.sp,
                    color = Color.DarkGray,
                    text = item.quantity.toString()
                )
                Text(
                    modifier = Modifier.padding(8.dp).clickable {
                        item.id?.let { itemId ->
                            incrementQuantity(itemId)
                        }
                    },
                    fontSize = 21.sp,
                    color = colorResource(id = R.color.accent),
                    fontWeight = FontWeight.Bold,
                    text = PLUS
                )
            }
        }
    }
}
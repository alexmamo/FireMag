package ro.alexmamo.firemag.components.cards

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.alexmamo.firemag.components.Price
import ro.alexmamo.firemag.core.AppConstants.NO_VALUE
import ro.alexmamo.firemag.domain.model.Order

@Composable
@ExperimentalMaterial3Api
fun OrderCard(
    order: Order,
    navigateToProductsOrderScreen: (orderId: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {
            val orderId = order.id ?: NO_VALUE
            navigateToProductsOrderScreen(orderId)
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            order.apply {
                Text(
                    text = dateOfSubmission.toString(),
                    fontSize = 14.sp
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Price(
                    price = total.toString(),
                    fontSize = 14.sp
                )
            }
        }
    }
}
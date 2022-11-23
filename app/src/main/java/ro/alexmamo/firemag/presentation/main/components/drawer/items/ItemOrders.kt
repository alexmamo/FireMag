package ro.alexmamo.firemag.presentation.main.components.drawer.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ro.alexmamo.firemag.components.cards.OrderCard
import ro.alexmamo.firemag.presentation.main.components.Orders

@Composable
@ExperimentalMaterial3Api
fun ItemOrders(
    navigateToProductsOrderScreen: (orderId: String) -> Unit
) {
    Orders { orders ->
        Column {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(orders) { order ->
                    OrderCard(
                        order = order,
                        navigateToProductsOrderScreen = navigateToProductsOrderScreen
                    )
                }
            }
        }
    }
}
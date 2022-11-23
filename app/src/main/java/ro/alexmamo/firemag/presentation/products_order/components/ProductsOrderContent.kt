package ro.alexmamo.firemag.presentation.products_order.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firemag.components.Price
import ro.alexmamo.firemag.components.ShortDivider
import ro.alexmamo.firemag.components.cards.OrderItemCard
import ro.alexmamo.firemag.core.AppConstants.PAID
import ro.alexmamo.firemag.core.Utils.Companion.calculateShoppingCartTotal
import ro.alexmamo.firemag.presentation.products_order.ProductsOrderViewModel

@Composable
@ExperimentalMaterial3Api
fun ProductsOrderContent(
    viewModel: ProductsOrderViewModel = hiltViewModel(),
    padding: PaddingValues,
    orderId: String
) {
    LaunchedEffect(Unit) {
        viewModel.getOrderShoppingCartItems(orderId)
    }
    ProductsOrder { items ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(items) { shoppingCartItem ->
                        OrderItemCard(
                            item = shoppingCartItem
                        )
                    }
                }
                ShortDivider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp
                        )
                ) {
                    Text(
                        text = PAID,
                        fontSize = 19.sp
                    )
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    Price(
                        price = calculateShoppingCartTotal(items).toString(),
                        fontSize = 19.sp
                    )
                }
            }
        }
    }
}
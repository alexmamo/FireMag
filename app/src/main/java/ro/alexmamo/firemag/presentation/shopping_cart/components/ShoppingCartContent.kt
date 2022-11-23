package ro.alexmamo.firemag.presentation.shopping_cart.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firemag.components.LargeButton
import ro.alexmamo.firemag.components.Message
import ro.alexmamo.firemag.components.Price
import ro.alexmamo.firemag.components.ShortDivider
import ro.alexmamo.firemag.components.cards.ShoppingCartItemCard
import ro.alexmamo.firemag.core.AppConstants.EMPTY_CART_MESSAGE
import ro.alexmamo.firemag.core.AppConstants.SEND_ORDER
import ro.alexmamo.firemag.core.AppConstants.TOTAL_PAYMENT
import ro.alexmamo.firemag.core.Utils.Companion.calculateShoppingCartTotal
import ro.alexmamo.firemag.presentation.shopping_cart.ShoppingCartViewModel

@Composable
@ExperimentalMaterial3Api
fun ShoppingCartContent(
    viewModel: ShoppingCartViewModel = hiltViewModel(),
    padding: PaddingValues,
    navigateToThankYouScreen: () -> Unit
) {
    ShoppingCart { items ->
        viewModel.numberOfItemsInShoppingCart = calculateShoppingCartTotal(items)
        
        if (items.isEmpty()) {
            Message(
                text = EMPTY_CART_MESSAGE
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(items) { shoppingCartItem ->
                        ShoppingCartItemCard(
                            item = shoppingCartItem,
                            incrementQuantity = { itemId ->
                                viewModel.incrementQuantity(itemId)
                            },
                            decrementQuantity = { itemId ->
                                viewModel.decrementQuantity(itemId)
                            }
                        )
                    }
                }
                Spacer(
                    modifier = Modifier.weight(1f)
                )
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
                        text = TOTAL_PAYMENT,
                        fontSize = 19.sp
                    )
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    Price(
                        price = viewModel.numberOfItemsInShoppingCart.toString(),
                        fontSize = 19.sp
                    )
                }
                LargeButton(
                    text = SEND_ORDER,
                    onClick = {
                        viewModel.addOrder(items)
                        navigateToThankYouScreen()
                    }
                )
            }
        }
    }

    AddOrder()
}
package ro.alexmamo.firemag.presentation.products_order.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firemag.components.ProgressBar
import ro.alexmamo.firemag.core.Utils.Companion.print
import ro.alexmamo.firemag.domain.model.Response.*
import ro.alexmamo.firemag.domain.repository.ShoppingCartItems
import ro.alexmamo.firemag.presentation.products_order.ProductsOrderViewModel

@Composable
fun ProductsOrder(
    viewModel: ProductsOrderViewModel = hiltViewModel(),
    shoppingCartItemsContent: @Composable (items: ShoppingCartItems) -> Unit
) {
    when(val productsOrderResponse = viewModel.productsOrderResponse) {
        is Loading -> ProgressBar()
        is Success -> productsOrderResponse.data?.let { items ->
            shoppingCartItemsContent(items)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(productsOrderResponse.e)
        }
    }
}
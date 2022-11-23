package ro.alexmamo.firemag.presentation.shopping_cart.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firemag.components.ProgressBar
import ro.alexmamo.firemag.core.Utils.Companion.print
import ro.alexmamo.firemag.domain.model.Response.*
import ro.alexmamo.firemag.domain.repository.ShoppingCartItems
import ro.alexmamo.firemag.presentation.shopping_cart.ShoppingCartViewModel

@Composable
fun ShoppingCart(
    viewModel: ShoppingCartViewModel = hiltViewModel(),
    shoppingCartContent: @Composable (items: ShoppingCartItems) -> Unit
) {
    when(val shoppingCartItemsResponse = viewModel.shoppingCartItemsResponse) {
        is Loading -> ProgressBar()
        is Success -> shoppingCartItemsResponse.data?.let { items ->
            shoppingCartContent(items)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(shoppingCartItemsResponse.e)
        }
    }
}
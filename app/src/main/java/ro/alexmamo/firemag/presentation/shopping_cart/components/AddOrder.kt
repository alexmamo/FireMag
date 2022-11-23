package ro.alexmamo.firemag.presentation.shopping_cart.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firemag.core.Utils.Companion.print
import ro.alexmamo.firemag.domain.model.Response.*
import ro.alexmamo.firemag.presentation.shopping_cart.ShoppingCartViewModel

@Composable
fun AddOrder(
    viewModel: ShoppingCartViewModel = hiltViewModel()
) {
    when(val addOrderResponse = viewModel.addOrderResponse) {
        is Loading -> Unit
        is Success -> Unit
        is Failure -> LaunchedEffect(Unit) {
            print(addOrderResponse.e)
        }
    }
}
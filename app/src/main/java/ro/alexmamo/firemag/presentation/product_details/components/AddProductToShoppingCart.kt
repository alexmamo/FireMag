package ro.alexmamo.firemag.presentation.product_details.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firemag.core.Utils.Companion.print
import ro.alexmamo.firemag.domain.model.Response.*
import ro.alexmamo.firemag.presentation.product_details.ProductDetailsViewModel

@Composable
fun AddProductToShoppingCart(
    shoppingCartViewModel: ProductDetailsViewModel = hiltViewModel()
) {
    when(val addProductToShoppingCartResponse =  shoppingCartViewModel.addProductToShoppingCartResponse) {
        is Loading -> Unit
        is Success -> Unit
        is Failure -> LaunchedEffect(Unit) {
            print(addProductToShoppingCartResponse.e)
        }
    }
}
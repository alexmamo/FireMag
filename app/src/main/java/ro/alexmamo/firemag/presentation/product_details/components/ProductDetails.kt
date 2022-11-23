package ro.alexmamo.firemag.presentation.product_details.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firemag.components.ProgressBar
import ro.alexmamo.firemag.core.Utils.Companion.print
import ro.alexmamo.firemag.domain.model.Product
import ro.alexmamo.firemag.domain.model.Response.*
import ro.alexmamo.firemag.presentation.product_details.ProductDetailsViewModel

@Composable
fun ProductDetails(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    productDetailsContent: @Composable (product: Product) -> Unit
) {
    when(val productDetailsResponse = viewModel.productDetailsResponse) {
        is Loading -> ProgressBar()
        is Success -> productDetailsResponse.data?.let { product ->
            productDetailsContent(product)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(productDetailsResponse.e)
        }
    }
}
package ro.alexmamo.firemag.presentation.main.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firemag.core.Utils.Companion.print
import ro.alexmamo.firemag.domain.model.Response.*
import ro.alexmamo.firemag.presentation.main.MainViewModel

@Composable
fun ShoppingCartSize(
    viewModel: MainViewModel = hiltViewModel(),
    shoppingCartSizeContent: @Composable (shoppingCartSize: Long) -> Unit
) {
    when(val shoppingCartSizeResponse = viewModel.shoppingCartSizeResponse) {
        is Loading -> Unit
        is Success -> shoppingCartSizeResponse.data?.let { shoppingCartSize ->
            shoppingCartSizeContent(shoppingCartSize)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(shoppingCartSizeResponse.e)
        }
    }
}
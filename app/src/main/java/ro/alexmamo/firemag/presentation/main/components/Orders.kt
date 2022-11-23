package ro.alexmamo.firemag.presentation.main.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firemag.components.ProgressBar
import ro.alexmamo.firemag.core.Utils.Companion.print
import ro.alexmamo.firemag.domain.model.Response.*
import ro.alexmamo.firemag.domain.repository.Orders
import ro.alexmamo.firemag.presentation.main.MainViewModel

@Composable
fun Orders(
    viewModel: MainViewModel = hiltViewModel(),
    ordersContent: @Composable (orders: Orders) -> Unit
) {
    when(val ordersResponse = viewModel.ordersResponse) {
        is Loading -> ProgressBar()
        is Success -> ordersResponse.data?.let { orders ->
            ordersContent(orders)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(ordersResponse.e)
        }
    }
}
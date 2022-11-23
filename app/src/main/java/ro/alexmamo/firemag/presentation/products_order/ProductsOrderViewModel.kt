package ro.alexmamo.firemag.presentation.products_order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.alexmamo.firemag.domain.model.Response.Loading
import ro.alexmamo.firemag.domain.repository.ProductsOrderRepository
import ro.alexmamo.firemag.domain.repository.ShoppingCartItemsResponse
import javax.inject.Inject

@HiltViewModel
class ProductsOrderViewModel @Inject constructor(
    private val repo: ProductsOrderRepository
): ViewModel() {
    var productsOrderResponse by mutableStateOf<ShoppingCartItemsResponse>(Loading)
        private set

    fun getOrderShoppingCartItems(orderId: String) = viewModelScope.launch {
        productsOrderResponse = repo.getOrderShoppingCartItemsFromFirestore(orderId)
    }
}
package ro.alexmamo.firemag.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.alexmamo.firemag.domain.model.Response
import ro.alexmamo.firemag.domain.model.ShoppingCartItem

typealias ShoppingCartItems = List<ShoppingCartItem>
typealias ShoppingCartItemsResponse = Response<ShoppingCartItems>
typealias IncrementQuantityResponse = Response<Boolean>
typealias DecrementQuantityResponse = Response<Boolean>
typealias AddOrderResponse = Response<Boolean>

interface ShoppingCartRepository {
    fun getShoppingCartItemsFromFirestore(): Flow<ShoppingCartItemsResponse>

    suspend fun incrementQuantity(itemId: String): IncrementQuantityResponse

    suspend fun decrementQuantity(itemId: String): DecrementQuantityResponse

    suspend fun addOrderInFirestore(items: ShoppingCartItems): AddOrderResponse
}
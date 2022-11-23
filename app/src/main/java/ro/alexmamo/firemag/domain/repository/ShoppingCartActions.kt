package ro.alexmamo.firemag.domain.repository

import ro.alexmamo.firemag.domain.model.ShoppingCartItem

interface ShoppingCartActions {
    suspend fun incrementShoppingCartQuantityInRealtimeDatabase()

    suspend fun incrementShoppingCartItemQuantityInFirestore(itemId: String)

    suspend fun addShoppingCartItemToFirestore(item: ShoppingCartItem)

    fun decrementShoppingCartQuantityInRealtimeDatabase()

    suspend fun decrementShoppingCartItemQuantityInFirestore(itemId: String)
}
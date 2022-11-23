package ro.alexmamo.firemag.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.alexmamo.firemag.domain.model.Product
import ro.alexmamo.firemag.domain.model.Response
import ro.alexmamo.firemag.domain.model.ShoppingCartItem

typealias ProductResponse = Response<Product>
typealias AddProductToFavoriteResponse = Response<Boolean>
typealias DeleteProductFromFavoritesResponse = Response<Boolean>
typealias AddProductToShoppingCartResponse = Response<Boolean>

interface ProductDetailsRepository {
    fun getProductFromFirestore(
        productId: String
    ): Flow<ProductResponse>

    suspend fun addProductToFavoritesInFirestore(
        productId: String,
        uid: String
    ): AddProductToFavoriteResponse

    suspend fun deleteProductFromFavoritesInFirestore(
        productId: String,
        uid: String
    ): DeleteProductFromFavoritesResponse

    suspend fun addProductToShoppingCartInFirestore(
        item: ShoppingCartItem
    ): AddProductToShoppingCartResponse
}
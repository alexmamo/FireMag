package ro.alexmamo.firemag.presentation.product_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.alexmamo.firemag.domain.model.Response.Loading
import ro.alexmamo.firemag.domain.model.Response.Success
import ro.alexmamo.firemag.domain.model.ShoppingCartItem
import ro.alexmamo.firemag.domain.repository.*
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repo: ProductDetailsRepository
): ViewModel() {
    var productDetailsResponse by mutableStateOf<ProductResponse>(Loading)
        private set
    var addProductToFavoritesResponse by mutableStateOf<AddProductToFavoriteResponse>(Success(false))
        private set
    var deleteProductFromFavoritesResponse by mutableStateOf<DeleteProductFromFavoritesResponse>(Success(false))
        private set
    var addProductToShoppingCartResponse by mutableStateOf<AddProductToShoppingCartResponse>(Success(false))
        private set

    fun getProduct(productId: String) = viewModelScope.launch {
        repo.getProductFromFirestore(productId).collect { response ->
            productDetailsResponse = response
        }
    }

    fun addProductToFavorites(
        productId: String,
        uid: String
    ) = viewModelScope.launch {
        addProductToFavoritesResponse = Loading
        addProductToFavoritesResponse = repo.addProductToFavoritesInFirestore(
            productId = productId,
            uid = uid
        )
    }

    fun deleteProductFromFavorites(
        productId: String,
        uid: String
    ) = viewModelScope.launch {
        deleteProductFromFavoritesResponse = Loading
        deleteProductFromFavoritesResponse = repo.deleteProductFromFavoritesInFirestore(
            productId = productId,
            uid = uid
        )
    }

    fun addProductToShoppingCart(
        item: ShoppingCartItem
    ) = viewModelScope.launch {
        addProductToShoppingCartResponse = Loading
        addProductToShoppingCartResponse = repo.addProductToShoppingCartInFirestore(
            item = item
        )
    }
}
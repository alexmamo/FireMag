package ro.alexmamo.firemag.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import ro.alexmamo.firemag.core.AppConstants.NO_VALUE
import ro.alexmamo.firemag.core.FirebaseConstants.FAVORITES
import ro.alexmamo.firemag.core.FirebaseConstants.PRODUCTS
import ro.alexmamo.firemag.domain.model.Product
import ro.alexmamo.firemag.domain.model.Response.Failure
import ro.alexmamo.firemag.domain.model.Response.Success
import ro.alexmamo.firemag.domain.model.ShoppingCartItem
import ro.alexmamo.firemag.domain.repository.AddProductToFavoriteResponse
import ro.alexmamo.firemag.domain.repository.AddProductToShoppingCartResponse
import ro.alexmamo.firemag.domain.repository.DeleteProductFromFavoritesResponse
import ro.alexmamo.firemag.domain.repository.ProductDetailsRepository

class ProductDetailsRepositoryImpl(
    firebaseDatabase: FirebaseDatabase,
    firebaseFirestore: FirebaseFirestore,
    auth: FirebaseAuth
): ProductDetailsRepository, ShoppingCartActionsImpl(firebaseDatabase, firebaseFirestore, auth) {
    private val productsRef = firebaseFirestore.collection(PRODUCTS)

    override fun getProductFromFirestore(
        productId: String
    ) = callbackFlow {
        val productIdRef = productsRef.document(productId)
        val snapshotListener = productIdRef.addSnapshotListener { snapshot, e ->
            val productResponse = if (snapshot != null) {
                val product = snapshot.toObject(Product::class.java)
                Success(product)
            } else {
                Failure(e)
            }
            trySend(productResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addProductToFavoritesInFirestore(
        productId: String,
        uid: String
    ): AddProductToFavoriteResponse {
        return try {
            productsRef.document(productId)
                .update(FAVORITES, FieldValue.arrayUnion(uid))
                .await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun deleteProductFromFavoritesInFirestore(
        productId: String,
        uid: String
    ): DeleteProductFromFavoritesResponse {
        return try {
            productsRef.document(productId)
                .update(FAVORITES, FieldValue.arrayRemove(uid))
                .await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun addProductToShoppingCartInFirestore(
        item: ShoppingCartItem
    ): AddProductToShoppingCartResponse {
        return try {
            val itemId = item.id ?: NO_VALUE
            if (!uidRef.get().await().exists()) {
                incrementShoppingCartQuantityInRealtimeDatabase()
                addShoppingCartItemToFirestore(item)
            } else {
                if (shoppingCartRef.document(itemId).get().await().exists()) {
                    incrementShoppingCartItemQuantityInFirestore(itemId)
                } else {
                    addShoppingCartItemToFirestore(item)
                }
                incrementShoppingCartQuantityInRealtimeDatabase()
            }
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}
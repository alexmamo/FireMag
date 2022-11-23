package ro.alexmamo.firemag.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ro.alexmamo.firemag.core.AppConstants.NO_VALUE
import ro.alexmamo.firemag.core.FirebaseConstants.PRODUCTS_ORDER
import ro.alexmamo.firemag.core.FirebaseConstants.USERS
import ro.alexmamo.firemag.domain.model.Response.Failure
import ro.alexmamo.firemag.domain.model.Response.Success
import ro.alexmamo.firemag.domain.model.ShoppingCartItems
import ro.alexmamo.firemag.domain.repository.ProductsOrderRepository
import ro.alexmamo.firemag.domain.repository.ShoppingCartItemsResponse

class ProductsOrderRepositoryImpl(
    db: FirebaseFirestore,
    auth: FirebaseAuth
): ProductsOrderRepository {
    private val uid = auth.currentUser?.uid ?: NO_VALUE
    private val productsOrdersRef = db.collection(USERS).document(uid).collection(PRODUCTS_ORDER)

    override suspend fun getOrderShoppingCartItemsFromFirestore(
        orderId: String
    ): ShoppingCartItemsResponse {
        return try {
            val orderIdRef = productsOrdersRef.document(orderId)
            val items = orderIdRef.get().await().toObject(ShoppingCartItems::class.java)?.items
            Success(items)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}
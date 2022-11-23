package ro.alexmamo.firemag.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ro.alexmamo.firemag.core.AppConstants.NO_VALUE
import ro.alexmamo.firemag.core.FirebaseConstants.QUANTITY
import ro.alexmamo.firemag.core.FirebaseConstants.SHOPPING_CART
import ro.alexmamo.firemag.core.FirebaseConstants.SHOPPING_CARTS
import ro.alexmamo.firemag.core.FirebaseConstants.USERS
import ro.alexmamo.firemag.domain.model.ShoppingCartItem
import ro.alexmamo.firemag.domain.repository.ShoppingCartActions

open class ShoppingCartActionsImpl(
    firebaseDatabase: FirebaseDatabase,
    private val firebaseFirestore: FirebaseFirestore,
    auth: FirebaseAuth
): ShoppingCartActions {
    val uid = auth.currentUser?.uid ?: NO_VALUE
    val uidRef = firebaseDatabase.getReference(SHOPPING_CARTS).child(uid)
    val shoppingCartRef = firebaseFirestore.collection(USERS).document(uid).collection(SHOPPING_CART)

    override suspend fun incrementShoppingCartQuantityInRealtimeDatabase() {
        uidRef.setValue(ServerValue.increment(1)).await()
    }

    override suspend fun incrementShoppingCartItemQuantityInFirestore(itemId: String) {
        shoppingCartRef.document(itemId).update(QUANTITY, FieldValue.increment(1)).await()
    }

    override suspend fun addShoppingCartItemToFirestore(item: ShoppingCartItem) {
        val itemId = item.id ?: NO_VALUE
        shoppingCartRef.document(itemId).set(item).await()
    }

    override fun decrementShoppingCartQuantityInRealtimeDatabase() {
        val transaction = object : Transaction.Handler {
            override fun doTransaction(mutableData: MutableData): Transaction.Result {
                val shoppingCartQuantity = mutableData.getValue(Long::class.java)
                    ?: return Transaction.success(mutableData)
                if (shoppingCartQuantity == 1L) {
                    mutableData.value = null
                } else {
                    mutableData.value = shoppingCartQuantity - 1
                }
                return Transaction.success(mutableData)
            }

            override fun onComplete(
                error: DatabaseError?,
                committed: Boolean,
                currentData: DataSnapshot?
            ) {
                error?.let {
                    throw it.toException()
                }
            }
        }
        uidRef.runTransaction(transaction)
    }

    override suspend fun decrementShoppingCartItemQuantityInFirestore(itemId: String) {
        firebaseFirestore.runTransaction { transaction ->
            val itemIdRef = shoppingCartRef.document(itemId)
            val snapshot = transaction.get(itemIdRef)
            snapshot.getLong(QUANTITY)?.let { currentQuantity ->
                if (currentQuantity == 1L) {
                    transaction.delete(itemIdRef)
                } else {
                    val newQuantity = currentQuantity - 1
                    transaction.update(itemIdRef, QUANTITY, newQuantity)
                }
            }
            null
        }.await()
    }
}
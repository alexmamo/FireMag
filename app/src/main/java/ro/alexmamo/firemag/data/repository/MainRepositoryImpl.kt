package ro.alexmamo.firemag.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.DESCENDING
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import ro.alexmamo.firemag.core.AppConstants.NO_VALUE
import ro.alexmamo.firemag.core.FirebaseConstants.BANNERS
import ro.alexmamo.firemag.core.FirebaseConstants.BRANDS
import ro.alexmamo.firemag.core.FirebaseConstants.DATE_OF_SUBMISSION
import ro.alexmamo.firemag.core.FirebaseConstants.FAVORITES
import ro.alexmamo.firemag.core.FirebaseConstants.ORDERS
import ro.alexmamo.firemag.core.FirebaseConstants.PAGE_SIZE
import ro.alexmamo.firemag.core.FirebaseConstants.POPULAR
import ro.alexmamo.firemag.core.FirebaseConstants.PRODUCTS
import ro.alexmamo.firemag.core.FirebaseConstants.SHOPPING_CARTS
import ro.alexmamo.firemag.core.FirebaseConstants.USERS
import ro.alexmamo.firemag.domain.model.Banner
import ro.alexmamo.firemag.domain.model.Brand
import ro.alexmamo.firemag.domain.model.Order
import ro.alexmamo.firemag.domain.model.Response.Failure
import ro.alexmamo.firemag.domain.model.Response.Success
import ro.alexmamo.firemag.domain.model.User
import ro.alexmamo.firemag.domain.repository.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseFirestore: FirebaseFirestore,
    private val config: PagingConfig
): MainRepository {
    override val user = User(
        uid = auth.currentUser?.uid ?: NO_VALUE,
        photoUrl = auth.currentUser?.photoUrl.toString(),
        displayName = auth.currentUser?.displayName ?: NO_VALUE,
        email = auth.currentUser?.email ?: NO_VALUE
    )
    private val uidRef = firebaseDatabase.getReference(SHOPPING_CARTS).child(user.uid)
    private val ordersRef = firebaseFirestore.collection(USERS).document(user.uid).collection(ORDERS)

    override suspend fun getBannersFromRealtimeDatabase(): BannersResponse {
        return try {
            val banners = mutableListOf<Banner>()
            firebaseDatabase.getReference(BANNERS).get().await().children.forEach { brandSnapshot ->
                brandSnapshot.children.forEach { bannerSnapshot ->
                    val banner = bannerSnapshot.toBanner()
                    banners.add(banner)
                }
            }
            Success(banners)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override fun getPopularProductsFromFirestore() = Pager(
        config = config
    ) {
        ProductsPagingSource(
            query = firebaseFirestore.collection(PRODUCTS)
                .whereEqualTo(POPULAR, true)
                .limit(PAGE_SIZE)
        )
    }.flow

    override suspend fun getBrandsFromRealtimeDatabase(): BrandsResponse {
        return try {
            val brands = mutableListOf<Brand>()
            firebaseDatabase.getReference(BRANDS).get().await().children.forEach { snapshot ->
                val brand = snapshot.toBrand()
                brands.add(brand)
            }
            Success(brands)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getOrdersFromFirestore(): OrdersResponse {
        return try {
            val queryOrdersByDateOfSubmission = ordersRef.orderBy(DATE_OF_SUBMISSION, DESCENDING)
            val orders = queryOrdersByDateOfSubmission.get().await().toObjects(Order::class.java)
            Success(orders)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override fun getFavoriteProductsFromFirestore(uid: String) = Pager(
        config = config
    ) {
        ProductsPagingSource(
            query = firebaseFirestore.collection(PRODUCTS)
                .whereArrayContains(FAVORITES, uid)
                .limit(PAGE_SIZE)
        )
    }.flow

    override fun getShoppingCartSizeFromRealtimeDatabase() = callbackFlow {
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val shoppingCartSize = snapshot.getValue<Long>()
                shoppingCartSize?.let {
                    trySend(Success(it))
                } ?: run {
                    trySend(Success(0L))
                }
            }

            override fun onCancelled(e: DatabaseError) {
                Failure(e.toException())
            }
        }
        uidRef.addValueEventListener(listener)
        awaitClose {
            uidRef.removeEventListener(listener)
        }
    }

    override suspend fun signOut(): SignOutResponse {
        return try {
            oneTapClient.signOut().await()
            auth.signOut()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}

fun DataSnapshot.toBrand() = Brand(
    name = key,
    token = getValue(String::class.java),

    )

fun DataSnapshot.toBanner() = Banner(
    productId = key,
    productBrand = ref.parent?.key,
    token = getValue(String::class.java)
)
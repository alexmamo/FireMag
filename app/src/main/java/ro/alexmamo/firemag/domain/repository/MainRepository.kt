package ro.alexmamo.firemag.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.alexmamo.firemag.domain.model.*

typealias Banners = List<Banner>
typealias BannersResponse = Response<Banners>
typealias Products = List<Product>
typealias Brands = List<Brand>
typealias BrandsResponse = Response<Brands>
typealias Orders = List<Order>
typealias OrdersResponse = Response<Orders>
typealias SignOutResponse = Response<Boolean>

interface MainRepository {
    val user: User

    suspend fun getBannersFromRealtimeDatabase(): BannersResponse

    fun getPopularProductsFromFirestore(): Flow<ProductPagingData>

    suspend fun getBrandsFromRealtimeDatabase(): BrandsResponse

    suspend fun getOrdersFromFirestore(): OrdersResponse

    fun getFavoriteProductsFromFirestore(uid: String): Flow<ProductPagingData>

    fun getShoppingCartSizeFromRealtimeDatabase(): Flow<Response<Long>>

    suspend fun signOut(): SignOutResponse
}
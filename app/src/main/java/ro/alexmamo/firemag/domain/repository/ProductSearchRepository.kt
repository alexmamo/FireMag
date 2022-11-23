package ro.alexmamo.firemag.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ro.alexmamo.firemag.domain.model.Product

typealias ProductPagingData = PagingData<Product>

interface ProductSearchRepository {
    fun getProductsFromFirestore(): Flow<ProductPagingData>

    fun getSearchProductsFromFirestore(searchText: String): Flow<ProductPagingData>
}
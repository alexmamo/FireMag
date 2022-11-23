package ro.alexmamo.firemag.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.DESCENDING
import ro.alexmamo.firemag.core.FirebaseConstants.CREATION_DATE
import ro.alexmamo.firemag.core.FirebaseConstants.NAME
import ro.alexmamo.firemag.core.FirebaseConstants.PAGE_SIZE
import ro.alexmamo.firemag.core.FirebaseConstants.PRODUCTS
import ro.alexmamo.firemag.domain.repository.ProductSearchRepository

class ProductSearchRepositoryImpl(
    private val db: FirebaseFirestore,
    private val config: PagingConfig
): ProductSearchRepository {
    override fun getProductsFromFirestore() = Pager(
        config = config
    ) {
        ProductsPagingSource(
            query = db.collection(PRODUCTS)
                .orderBy(CREATION_DATE, DESCENDING)
                .limit(PAGE_SIZE)
        )
    }.flow

    override fun getSearchProductsFromFirestore(searchText: String) = Pager(
        config = config
    ) {
        ProductsPagingSource(
            query = db.collection(PRODUCTS)
                .orderBy(NAME)
                .startAt(searchText)
                .endAt("$searchText\uf8ff")
                .limit(PAGE_SIZE)
        )
    }.flow
}
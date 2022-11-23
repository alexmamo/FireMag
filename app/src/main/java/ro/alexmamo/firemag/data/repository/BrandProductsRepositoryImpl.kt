package ro.alexmamo.firemag.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.google.firebase.firestore.FirebaseFirestore
import ro.alexmamo.firemag.core.FirebaseConstants.BRAND
import ro.alexmamo.firemag.core.FirebaseConstants.PAGE_SIZE
import ro.alexmamo.firemag.core.FirebaseConstants.PRODUCTS
import ro.alexmamo.firemag.domain.repository.BrandProductsRepository

class BrandProductsRepositoryImpl(
    private val db: FirebaseFirestore,
    private val config: PagingConfig
): BrandProductsRepository {
    override fun getBrandProductsFromFirestore(brand: String) = Pager(
        config = config
    ) {
        ProductsPagingSource(
            query = db.collection(PRODUCTS).whereEqualTo(BRAND, brand).limit(PAGE_SIZE)
        )
    }.flow
}
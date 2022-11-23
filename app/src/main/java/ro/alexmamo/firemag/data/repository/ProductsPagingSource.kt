package ro.alexmamo.firemag.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import ro.alexmamo.firemag.domain.model.Product

class ProductsPagingSource(
    private val query: Query
) : PagingSource<QuerySnapshot, Product>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, Product>): QuerySnapshot? = null

    override suspend fun load(params: LoadParams<QuerySnapshot>) = try {
        val currentPage = params.key ?: query.get().await()
        val lastVisibleDocument = currentPage.documents[currentPage.size() - 1]
        val nextPage = query.startAfter(lastVisibleDocument).get().await()
        LoadResult.Page(
            data = currentPage.toObjects(Product::class.java),
            prevKey = null,
            nextKey = nextPage
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}
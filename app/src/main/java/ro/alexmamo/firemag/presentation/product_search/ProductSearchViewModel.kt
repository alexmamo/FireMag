package ro.alexmamo.firemag.presentation.product_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ro.alexmamo.firemag.domain.repository.ProductSearchRepository
import javax.inject.Inject

@HiltViewModel
class ProductSearchViewModel @Inject constructor(
    private val repo: ProductSearchRepository
): ViewModel() {
    fun getSearchProducts(searchText: String) = if (searchText.isEmpty()) {
        repo.getProductsFromFirestore()
    } else {
        repo.getSearchProductsFromFirestore(searchText)
    }.cachedIn(viewModelScope)
}
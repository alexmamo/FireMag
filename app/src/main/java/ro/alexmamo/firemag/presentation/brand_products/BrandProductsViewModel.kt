package ro.alexmamo.firemag.presentation.brand_products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ro.alexmamo.firemag.domain.repository.BrandProductsRepository
import javax.inject.Inject

@HiltViewModel
class BrandProductsViewModel @Inject constructor(
    private val repo: BrandProductsRepository
): ViewModel() {
    fun getBrandProducts(brand: String) = repo.getBrandProductsFromFirestore(brand).cachedIn(viewModelScope)
}
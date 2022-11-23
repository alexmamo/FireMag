package ro.alexmamo.firemag.presentation.brand_products.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import ro.alexmamo.firemag.components.layouts.VerticalContent
import ro.alexmamo.firemag.presentation.brand_products.BrandProductsViewModel

@Composable
@ExperimentalMaterial3Api
fun BrandProductsContent(
    viewModel: BrandProductsViewModel = hiltViewModel(),
    padding: PaddingValues,
    productBrand: String,
    navigateToProductDetailsScreen: (productId: String) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(padding),
        contentAlignment = Alignment.Center
    ) {
        VerticalContent(
            pagingProducts = viewModel.getBrandProducts(productBrand).collectAsLazyPagingItems(),
            navigateToProductDetailsScreen = navigateToProductDetailsScreen
        )
    }
}
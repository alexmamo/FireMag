package ro.alexmamo.firemag.presentation.main.components.drawer.items

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import ro.alexmamo.firemag.components.Title
import ro.alexmamo.firemag.components.cards.BannerCard
import ro.alexmamo.firemag.components.icons.BrandsHorizontalContent
import ro.alexmamo.firemag.components.layouts.HorizontalContent
import ro.alexmamo.firemag.core.AppConstants.BRANDS_TITLE
import ro.alexmamo.firemag.core.AppConstants.POPULAR_PRODUCTS_TITLE
import ro.alexmamo.firemag.presentation.main.MainViewModel
import ro.alexmamo.firemag.presentation.main.components.Banners
import ro.alexmamo.firemag.presentation.main.components.Brands

@Composable
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
fun ItemHome(
    viewModel: MainViewModel = hiltViewModel(),
    navigateToProductDetailsScreen: (productId: String) -> Unit,
    navigateToBrandProductsScreen: (productBrand: String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Banners { banners ->
            BannerCard(
                banner = banners.first(),
                navigateToProductDetailsScreen = navigateToProductDetailsScreen
            )
        }
        Title(POPULAR_PRODUCTS_TITLE)
        HorizontalContent(
            pagingPopularProducts = viewModel.getPopularProducts().collectAsLazyPagingItems(),
            navigateToProductDetailsScreen = navigateToProductDetailsScreen
        )
        Title(BRANDS_TITLE)
        Brands(
            brandsContent = { brands ->
                BrandsHorizontalContent(
                    brands = brands,
                    navigateToBrandProductsScreen = navigateToBrandProductsScreen
                )
            }
        )
    }
}
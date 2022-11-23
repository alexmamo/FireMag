package ro.alexmamo.firemag.presentation.brand_products

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import ro.alexmamo.firemag.components.AppTopBar
import ro.alexmamo.firemag.core.AppConstants.SHOES
import ro.alexmamo.firemag.presentation.brand_products.components.BrandProductsContent
import java.util.*

@Composable
@ExperimentalMaterial3Api
fun BrandProductsScreen(
    productBrand: String,
    navigateToProductSearchScreen: () -> Unit,
    navigateToShoppingCartScreen: () -> Unit,
    navigateToProductDetailsScreen: (productId: String) -> Unit,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "${productBrand.capitalizeFirstChar()} $SHOES",
                navigateBack = navigateBack,
                onSearchIconClick = navigateToProductSearchScreen,
                onShoppingCartIconClick = navigateToShoppingCartScreen
            )
        },
        content = { padding ->
            BrandProductsContent(
                padding = padding,
                productBrand = productBrand,
                navigateToProductDetailsScreen = navigateToProductDetailsScreen
            )
        }
    )
}

fun String.capitalizeFirstChar() = this.replaceFirstChar { char ->
    if (char.isLowerCase()) {
        char.titlecase(Locale.getDefault())
    } else {
        char.toString()
    }
}
package ro.alexmamo.firemag.components.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import ro.alexmamo.firemag.components.cards.ProductCard
import ro.alexmamo.firemag.domain.model.Product

@Composable
@ExperimentalMaterial3Api
fun HorizontalContent(
    pagingPopularProducts: LazyPagingItems<Product>,
    navigateToProductDetailsScreen: (productId: String) -> Unit,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(pagingPopularProducts) { product ->
            product?.let {
                ProductCard(
                    product = product,
                    onClick = {
                        product.id?.let { productId ->
                            navigateToProductDetailsScreen(productId)
                        }
                    }
                )
            }
        }
    }
}
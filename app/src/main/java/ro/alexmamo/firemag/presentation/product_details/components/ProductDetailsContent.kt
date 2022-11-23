package ro.alexmamo.firemag.presentation.product_details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firemag.components.LargeButton
import ro.alexmamo.firemag.components.Price
import ro.alexmamo.firemag.components.ShortDivider
import ro.alexmamo.firemag.components.icons.FavoriteBorderIcon
import ro.alexmamo.firemag.components.icons.FavoriteIcon
import ro.alexmamo.firemag.core.AppConstants.ADD_TO_CART
import ro.alexmamo.firemag.core.AppConstants.NO_VALUE
import ro.alexmamo.firemag.domain.model.toShoppingCartItem
import ro.alexmamo.firemag.presentation.main.MainViewModel
import ro.alexmamo.firemag.presentation.product_details.ProductDetailsViewModel

@Composable
fun ProductDetailsContent(
    productDetailsViewModel: ProductDetailsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
    padding: PaddingValues,
    productId: String
) {
    val uid = mainViewModel.user.uid
    LaunchedEffect(productId) {
        productDetailsViewModel.getProduct(productId)
    }
    ProductDetails { product ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                product.apply {
                    val token = image ?: NO_VALUE
                    Box(
                        contentAlignment = Alignment.TopEnd
                    ) {
                        ProductImage(
                            productId = productId,
                            token = token
                        )
                        if (favorites != null && favorites.isNotEmpty() && favorites.contains(mainViewModel.user.uid)) {
                            FavoriteIcon(
                                onClick = {
                                    productDetailsViewModel.deleteProductFromFavorites(productId, uid)
                                }
                            )
                        } else {
                            FavoriteBorderIcon(
                                onClick = {
                                    productDetailsViewModel.addProductToFavorites(productId, uid)
                                }
                            )
                        }
                    }
                    ShortDivider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 8.dp
                            ),
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = name.toString(),
                            fontSize = 21.sp,
                            color = Color.DarkGray
                        )
                        Price(
                            price = price.toString(),
                            fontSize = 21.sp
                        )
                    }
                }
            }
            LargeButton(
                text = ADD_TO_CART,
                onClick = {
                    productDetailsViewModel.addProductToShoppingCart(product.toShoppingCartItem())
                }
            )
        }
    }

    AddProductToShoppingCart()
}
package ro.alexmamo.firemag.presentation.shopping_cart.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import ro.alexmamo.firemag.R
import ro.alexmamo.firemag.core.AppConstants.SHOPPING_CART_SCREEN

@Composable
@ExperimentalMaterial3Api
fun ShoppingCartTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = SHOPPING_CART_SCREEN,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(R.color.primary),
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}
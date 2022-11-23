package ro.alexmamo.firemag.presentation.shopping_cart

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import ro.alexmamo.firemag.presentation.shopping_cart.components.ShoppingCartContent
import ro.alexmamo.firemag.presentation.shopping_cart.components.ShoppingCartTopBar

@Composable
@ExperimentalMaterial3Api
fun ShoppingCartScreen(
    navigateBack: () -> Unit,
    navigateToThankYouScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            ShoppingCartTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            ShoppingCartContent(
                padding = padding,
                navigateToThankYouScreen = navigateToThankYouScreen
            )
        },
    )
}
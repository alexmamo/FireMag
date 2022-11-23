package ro.alexmamo.firemag.navigation

import androidx.navigation.NavHostController
import ro.alexmamo.firemag.navigation.Screen.*

class Direction(
    navController: NavHostController
) {
    val navigateToHomeScreen: () -> Unit = {
        navController.navigate(MainScreen.route)
    }

    val navigateToAuthScreen: () -> Unit = {
        navController.popBackStack()
        navController.navigate(AuthScreen.route)
    }

    val navigateToProductSearchScreen: () -> Unit = {
        navController.navigate(ProductSearchScreen.route)
    }

    val navigateToShoppingCartScreen: () -> Unit = {
        navController.navigate(ShoppingCartScreen.route)
    }

    val navigateToThankYouScreen: () -> Unit = {
        navController.navigate(ThankYouScreen.route)
    }

    val navigateBackToMainScreen: () -> Unit = {
        navController.navigate(MainScreen.route) {
            popUpTo(MainScreen.route) {
                inclusive = true
            }
        }
    }

    val navigateToBrandProductsScreen: (String) -> Unit = { productBrand ->
        navController.navigate("${BrandProductsScreen.route}/$productBrand")
    }

    val navigateToProductsOrderScreen: (String) -> Unit = { orderId ->
        navController.navigate("${ProductsOrderScreen.route}/$orderId")
    }

    val navigateToProductDetailsScreen: (String) -> Unit = { productId ->
        navController.navigate("${ProductDetailsScreen.route}/$productId")
    }

    val navigateBack: () -> Unit = {
        navController.navigateUp()
    }
}
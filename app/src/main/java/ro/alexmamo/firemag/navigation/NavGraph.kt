package ro.alexmamo.firemag.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import ro.alexmamo.firemag.core.AppConstants.NO_VALUE
import ro.alexmamo.firemag.core.AppConstants.ORDER_ID
import ro.alexmamo.firemag.core.AppConstants.PRODUCT_BRAND
import ro.alexmamo.firemag.core.AppConstants.PRODUCT_ID
import ro.alexmamo.firemag.navigation.Screen.*
import ro.alexmamo.firemag.presentation.auth.AuthScreen
import ro.alexmamo.firemag.presentation.brand_products.BrandProductsScreen
import ro.alexmamo.firemag.presentation.main.MainScreen
import ro.alexmamo.firemag.presentation.product_details.ProductDetailsScreen
import ro.alexmamo.firemag.presentation.product_search.ProductSearchScreen
import ro.alexmamo.firemag.presentation.products_order.ProductsOrderScreen
import ro.alexmamo.firemag.presentation.shopping_cart.ShoppingCartScreen
import ro.alexmamo.firemag.presentation.thank_you.ThankYouScreen

@Composable
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
fun NavGraph(
    navController: NavHostController
) {
    val direction = remember(navController) { Direction(navController) }

    AnimatedNavHost(
        navController = navController,
        startDestination = AuthScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(
            route = AuthScreen.route
        ) {
            AuthScreen(
                navigateToHomeScreen = {
                    direction.navigateToHomeScreen()
                }
            )
        }
        composable(
            route = MainScreen.route
        ) {
            MainScreen(
                navigateToProductSearchScreen = {
                    direction.navigateToProductSearchScreen()
                },
                navigateToShoppingCartScreen = {
                    direction.navigateToShoppingCartScreen()
                },
                navigateToProductDetailsScreen = { productId ->
                    direction.navigateToProductDetailsScreen(productId)
                },
                navigateToBrandProductsScreen = { productBrand ->
                    direction.navigateToBrandProductsScreen(productBrand)
                },
                navigateToProductsOrderScreen = { orderId ->
                    direction.navigateToProductsOrderScreen(orderId)
                },
                navigateToAuthScreen = {
                    direction.navigateToAuthScreen()
                }
            )
        }
        composable(
            route = ProductSearchScreen.route
        ) {
            ProductSearchScreen(
                navigateBack = {
                    direction.navigateBack()
                },
                navigateToProductDetailsScreen = { productId ->
                    direction.navigateToProductDetailsScreen(productId)
                }
            )
        }
        composable(
            route = ShoppingCartScreen.route
        ) {
            ShoppingCartScreen(
                navigateBack = {
                    direction.navigateBack()
                },
                navigateToThankYouScreen = {
                    direction.navigateToThankYouScreen()
                }
            )
        }
        composable(
            route = "${ProductsOrderScreen.route}/{$ORDER_ID}",
            arguments = listOf(
                navArgument(ORDER_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString(ORDER_ID) ?: NO_VALUE
            ProductsOrderScreen(
                orderId = orderId,
                navigateBack = {
                    direction.navigateBack()
                },
                navigateToProductSearchScreen = {
                    direction.navigateToProductSearchScreen()
                },
                navigateToShoppingCartScreen = {
                    direction.navigateToShoppingCartScreen()
                }
            )
        }
        composable(
            route = ThankYouScreen.route
        ) {
            ThankYouScreen(
                navigateBackToMainScreen = {
                    direction.navigateBackToMainScreen()
                }
            )
        }
        composable(
            route = "${BrandProductsScreen.route}/{$PRODUCT_BRAND}",
            arguments = listOf(
                navArgument(PRODUCT_BRAND) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val productBrand = backStackEntry.arguments?.getString(PRODUCT_BRAND) ?: NO_VALUE
            BrandProductsScreen(
                productBrand = productBrand,
                navigateToProductSearchScreen = {
                    direction.navigateToProductSearchScreen()
                },
                navigateToShoppingCartScreen = {
                    direction.navigateToShoppingCartScreen()
                },
                navigateToProductDetailsScreen = { productId ->
                    direction.navigateToProductDetailsScreen(productId)
                },
                navigateBack = {
                    direction.navigateBack()
                },
            )
        }
        composable(
            route = "${ProductDetailsScreen.route}/{$PRODUCT_ID}",
            arguments = listOf(
                navArgument(PRODUCT_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(PRODUCT_ID) ?: NO_VALUE
            ProductDetailsScreen(
                productId = productId,
                navigateToProductSearchScreen = {
                    direction.navigateToProductSearchScreen()
                },
                navigateToShoppingCartScreen = {
                    direction.navigateToShoppingCartScreen()
                },
                navigateBack = {
                    direction.navigateBack()
                }
            )
        }
    }
}
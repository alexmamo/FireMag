package ro.alexmamo.firemag.components

import androidx.compose.runtime.Composable
import ro.alexmamo.firemag.components.icons.SearchIcon
import ro.alexmamo.firemag.components.icons.ShoppingCartIcon

@Composable
fun TopBarActions(
    onSearchIconClick: () -> Unit,
    onShoppingCartIconClick: () -> Unit
) {
    SearchIcon(
        onSearchIconClick = onSearchIconClick
    )
    ShoppingCartIcon(
        onShoppingCartIconClick = onShoppingCartIconClick
    )
}
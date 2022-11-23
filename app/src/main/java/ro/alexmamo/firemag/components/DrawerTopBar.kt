package ro.alexmamo.firemag.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ro.alexmamo.firemag.R
import ro.alexmamo.firemag.components.icons.NavigationIcon

@Composable
@ExperimentalMaterial3Api
fun DrawerTopBar(
    openNavigationDrawer: () -> Unit,
    onSearchIconClick: () -> Unit,
    onShoppingCartIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(
                    id = R.string.app_name
                ),
                color = Color.White
            )
        },
        navigationIcon = {
            NavigationIcon(
                openNavigationDrawer = openNavigationDrawer
            )
        },
        actions = {
            TopBarActions(
                onSearchIconClick = onSearchIconClick,
                onShoppingCartIconClick = onShoppingCartIconClick
            )
        }, colors = getTopBarColors()
    )
}
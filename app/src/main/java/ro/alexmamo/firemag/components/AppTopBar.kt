package ro.alexmamo.firemag.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import ro.alexmamo.firemag.R
import ro.alexmamo.firemag.components.icons.BackIcon

@Composable
@ExperimentalMaterial3Api
fun AppTopBar(
    title: String,
    navigateBack: () -> Unit,
    onSearchIconClick: () -> Unit,
    onShoppingCartIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        },
        actions = {
            TopBarActions(
                onSearchIconClick = onSearchIconClick,
                onShoppingCartIconClick = onShoppingCartIconClick
            )
        },
        colors = getTopBarColors()
    )
}

@Composable
@ExperimentalMaterial3Api
fun getTopBarColors() = TopAppBarDefaults.smallTopAppBarColors(
    containerColor = colorResource(R.color.primary),
    navigationIconContentColor = Color.White,
    actionIconContentColor = Color.White
)
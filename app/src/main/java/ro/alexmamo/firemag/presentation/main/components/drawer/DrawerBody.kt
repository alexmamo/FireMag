package ro.alexmamo.firemag.presentation.main.components.drawer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ro.alexmamo.firemag.R
import ro.alexmamo.firemag.core.Utils.Companion.items
import ro.alexmamo.firemag.presentation.main.MainViewModel

@Composable
@ExperimentalMaterial3Api
fun DrawerBody(
    viewModel: MainViewModel = hiltViewModel(),
    drawerState: DrawerState,
    coroutineScope: CoroutineScope
) {
    items.forEach { drawerItem ->
        NavigationDrawerItem(
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
            icon = {
                Icon(
                    imageVector = drawerItem.icon,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = drawerItem.name
                )
            },
            selected = drawerItem == viewModel.selectedItem,
            onClick = {
                coroutineScope.launch {
                    drawerState.close()
                    viewModel.selectedItem = drawerItem
                }
            },
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = colorResource(R.color.primary_dark),
                unselectedContainerColor = colorResource(R.color.primary),
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White
            ),
            shape = ShapeDefaults.ExtraSmall
        )
    }
}
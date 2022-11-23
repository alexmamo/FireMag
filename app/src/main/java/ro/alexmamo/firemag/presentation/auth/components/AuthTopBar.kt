package ro.alexmamo.firemag.presentation.auth.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ro.alexmamo.firemag.R
import ro.alexmamo.firemag.components.getTopBarColors
import ro.alexmamo.firemag.core.AppConstants.AUTH_SCREEN

@Composable
@ExperimentalMaterial3Api
fun AuthTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(
                    id = R.string.app_name
                ) + " $AUTH_SCREEN",
                color = Color.White
            )
        },
        colors = getTopBarColors()
    )
}
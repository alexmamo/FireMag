package ro.alexmamo.firemag.components.icons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firemag.R
import ro.alexmamo.firemag.presentation.main.MainViewModel
import ro.alexmamo.firemag.presentation.main.components.ShoppingCartSize

@Composable
fun ShoppingCartIcon(
    viewModel: MainViewModel = hiltViewModel(),
    onShoppingCartIconClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getShoppingCartSize()
    }
    val circleColor = colorResource(R.color.accent)
    Box {
        IconButton(
            onClick = onShoppingCartIconClick
        ) {
            Icon(
                Icons.Filled.ShoppingCart,
                contentDescription = null
            )
        }
        ShoppingCartSize { size ->
            if (size > 0) {
                Text(
                    modifier = Modifier.align(Alignment.TopEnd)
                        .offset(
                            x = (-8).dp
                        )
                        .drawBehind {
                            drawCircle(
                                color = circleColor,
                                radius = 24.00f
                            )
                        },
                    text = size.toString(),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
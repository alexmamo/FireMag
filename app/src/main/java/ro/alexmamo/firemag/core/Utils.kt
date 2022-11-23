package ro.alexmamo.firemag.core

import android.net.Uri
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import ro.alexmamo.firemag.R
import ro.alexmamo.firemag.core.AppConstants.FAVORITES
import ro.alexmamo.firemag.core.AppConstants.HOME
import ro.alexmamo.firemag.core.AppConstants.ORDERS
import ro.alexmamo.firemag.core.AppConstants.PROFILE
import ro.alexmamo.firemag.core.AppConstants.SIGN_OUT
import ro.alexmamo.firemag.core.AppConstants.TAG
import ro.alexmamo.firemag.core.AppConstants.PNG
import ro.alexmamo.firemag.core.FirebaseConstants.STORAGE_BASE_URL
import ro.alexmamo.firemag.domain.model.Image
import ro.alexmamo.firemag.domain.model.Item
import ro.alexmamo.firemag.domain.repository.ShoppingCartItems

class Utils {
    companion object {
        fun print(e: Exception?) {
            Log.e(TAG, e?.message ?: e.toString())
        }

        fun print(errorState: LoadState.Error) {
            val error = errorState.error
            Log.d(TAG, error.message ?: error.toString())
        }

        val items = listOf(
            Item(Icons.Default.Home, HOME),
            Item(Icons.Default.List, ORDERS),
            Item(Icons.Default.Favorite, FAVORITES),
            Item(Icons.Default.Person, PROFILE),
            Item(Icons.Default.ExitToApp, SIGN_OUT)
        )

        @Composable
        fun getImageUrl(image: Image, name: String, token: String): String {
            val projectId = LocalContext.current.getString(R.string.firebase_project_id)
            val slash = Uri.encode("/")
            return "$STORAGE_BASE_URL/$projectId/o/${image.folder}$slash$name.$PNG?alt=media&token=$token"
        }

        fun calculateShoppingCartTotal(items: ShoppingCartItems) = items.sumOf { item ->
            val price = item.price ?: 0
            val quantity = item.quantity ?: 0
            price * quantity
        }
    }
}
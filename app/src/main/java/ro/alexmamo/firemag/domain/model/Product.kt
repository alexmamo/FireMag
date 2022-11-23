package ro.alexmamo.firemag.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Product (
    val brand: String? = null,
    @ServerTimestamp
    val creationDate: Date? = null,
    val favorites: List<String>? = null,
    val id: String? = null,
    val image: String? = null,
    val name: String? = null,
    val popular: Boolean? = null,
    val price: Int? = null,
    val thumb: String? = null

)

fun Product.toShoppingCartItem() = ShoppingCartItem(
    id = id,
    name = name,
    price = price,
    quantity = 1,
    thumb = thumb
)
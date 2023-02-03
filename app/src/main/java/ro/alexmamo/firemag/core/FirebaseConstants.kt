package ro.alexmamo.firemag.core

object FirebaseConstants {
    //Realtime Database Nodes and Firestore Collections
    const val BANNERS = "banners"
    const val BRANDS = "brands"
    const val SHOPPING_CARTS = "shoppingCarts"
    const val USERS = "users"
    const val PRODUCTS = "products"
    const val ORDERS = "orders"
    const val PRODUCTS_ORDER = "productsOrder"
    const val SHOPPING_CART = "shoppingCart"

    //Firebase Fields
    const val CREATED_AT = "createdAt"
    const val DISPLAY_NAME = "displayName"
    const val EMAIL = "email"
    const val BRAND = "brand"
    const val PHOTO_URL = "photoUrl"
    const val CREATION_DATE = "creationDate"
    const val FAVORITES = "favorites"
    const val NAME = "name"
    const val POPULAR = "popular"
    const val QUANTITY = "quantity"
    const val ADDITION_DATE = "additionDate"
    const val ITEMS = "items"
    const val ID = "id"
    const val DATE_OF_SUBMISSION = "dateOfSubmission"
    const val TOTAL = "total"

    //Cloud Storage Folders
    const val THUMBS = "thumbs"
    const val IMAGES = "images"

    //Paging Limit
    const val PAGE_SIZE = 8L

    //Base URLs
    const val STORAGE_BASE_URL = "https://firebasestorage.googleapis.com/v0/b"
}
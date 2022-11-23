package ro.alexmamo.firemag.domain.model

data class User (
    val uid: String,
    val photoUrl: String,
    val displayName: String,
    val email: String
)
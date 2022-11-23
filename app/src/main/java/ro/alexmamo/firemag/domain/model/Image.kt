package ro.alexmamo.firemag.domain.model

import ro.alexmamo.firemag.core.FirebaseConstants.BANNERS
import ro.alexmamo.firemag.core.FirebaseConstants.BRANDS
import ro.alexmamo.firemag.core.FirebaseConstants.IMAGES
import ro.alexmamo.firemag.core.FirebaseConstants.THUMBS

sealed class Image(val folder: String) {
    object BannerImage: Image(BANNERS)
    object BrandImage: Image(BRANDS)
    object ProductThumbImage: Image(THUMBS)
    object ProductImageImage: Image(IMAGES)
}
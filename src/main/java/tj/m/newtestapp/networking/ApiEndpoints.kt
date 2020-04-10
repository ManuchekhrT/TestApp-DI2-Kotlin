package tj.m.newtestapp.networking

object ApiEndpoints {
    const val API_BASE: String = "https://api.imgur.com/3/"
    const val API_IMAGE: String = "image/{(imageHash}}"
    const val API_TAGS: String = "tags"
    const val API_GALLERY_TAG: String = "gallery/t/{tagName}/{sort}/{window}/{page}"
}
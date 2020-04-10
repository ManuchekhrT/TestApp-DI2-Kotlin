package tj.m.newtestapp.data.api.model.images

data class Image(
    var id: String,
    var title: String?,
    var description: String?,
    var link: String?,
    var images: List<Image>,
    var cover: String,
    var isAlbum: Boolean
)
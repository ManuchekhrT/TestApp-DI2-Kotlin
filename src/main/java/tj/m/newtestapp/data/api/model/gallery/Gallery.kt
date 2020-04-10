package tj.m.newtestapp.data.api.model.gallery

data class Gallery(
    var id: Int?,
    var name: String?,
    var description: String?,
    var image: String?,
    var published_at: String?,
    var topPost: TopPost
)
package tj.m.newtestapp.data.api.model.tags

import tj.m.newtestapp.data.api.model.images.Image

data class Tag (
    var display_name: String,
    var name: String,
    var background_hash: String,
    var items: List<Image>?

)
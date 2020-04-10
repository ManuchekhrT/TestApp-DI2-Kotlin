package tj.m.newtestapp.data.api.model.gallery

import tj.m.newtestapp.data.api.model.tags.Tag

data class GalleriesTagRoot(
    var data: Tag,
    var success: Boolean,
    var status: Int
)
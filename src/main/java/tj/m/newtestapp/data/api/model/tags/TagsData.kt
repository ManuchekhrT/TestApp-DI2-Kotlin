package tj.m.newtestapp.data.api.model.tags

import tj.m.newtestapp.data.api.model.gallery.Gallery

data class TagsData(
    var tags: List<Tag>,
    var featured: String,
    var galleries: List<Gallery>?
)
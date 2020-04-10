package tj.m.newtestapp.data.repository

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import tj.m.newtestapp.data.api.model.gallery.GalleriesTagRoot
import tj.m.newtestapp.data.api.model.images.ImageRoot
import tj.m.newtestapp.data.api.model.tags.TagsRoot
import tj.m.newtestapp.networking.ApiEndpoints

interface PostApi {

    @GET(ApiEndpoints.API_IMAGE)
    fun getImageAsync(@Path("imageHash") imgHash: String): Deferred<ImageRoot>

    @GET(ApiEndpoints.API_GALLERY_TAG)
    fun getPostsFromTagAsync(
        @Path("tagName") tagName: String, @Path("sort") sort: String,
        @Path("window") window: String, @Path("page") page: Int
    ): Deferred<GalleriesTagRoot>

    @GET(ApiEndpoints.API_TAGS)
    fun getTagsAsync(): Deferred<TagsRoot>
}
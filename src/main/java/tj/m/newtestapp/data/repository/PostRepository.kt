package tj.m.newtestapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.m.newtestapp.data.api.model.gallery.GalleriesTagRoot
import tj.m.newtestapp.data.api.model.tags.TagsRoot
import tj.m.newtestapp.di.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository : BaseRepository() {

    @Inject
    lateinit var postApi: PostApi

    fun getTags(): LiveData<TagsRoot> {
        val tagsRoot = MutableLiveData<TagsRoot>()

        GlobalScope.launch {
            val posts = postApi.getTagsAsync().await()
            tagsRoot.postValue(posts)
        }

        return tagsRoot
    }

    fun getPosts(tagTitle: String, s1: String, s2: String, page: Int): LiveData<GalleriesTagRoot> {
        val galleriesTagRoot = MutableLiveData<GalleriesTagRoot>()

        GlobalScope.launch {
            val galleries = postApi.getPostsFromTagAsync(tagTitle, s1, s2, page).await()
            galleriesTagRoot.postValue(galleries)
        }

        return galleriesTagRoot
    }

}
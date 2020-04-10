package tj.m.newtestapp.ui.gallery

import androidx.lifecycle.LiveData
import tj.m.newtestapp.data.repository.PostRepository
import tj.m.newtestapp.data.api.model.gallery.GalleriesTagRoot
import tj.m.newtestapp.di.BaseViewModel
import javax.inject.Inject

class GalleryViewModel : BaseViewModel() {

    @Inject
    lateinit var postRepository: PostRepository


    fun getPostsFromTag(
        tagTitle: String,
        s1: String,
        s2: String,
        page: Int
    ): LiveData<GalleriesTagRoot> {
        return postRepository.getPosts(tagTitle, s1, s2, page)
    }
}

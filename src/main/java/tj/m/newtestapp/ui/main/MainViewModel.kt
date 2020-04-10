package tj.m.newtestapp.ui.main

import androidx.lifecycle.LiveData
import tj.m.newtestapp.data.repository.PostRepository
import tj.m.newtestapp.data.api.model.tags.TagsRoot
import tj.m.newtestapp.di.BaseViewModel
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    @Inject
    lateinit var postRepository: PostRepository


    fun getTags(): LiveData<TagsRoot> {
        return postRepository.getTags()
    }
}

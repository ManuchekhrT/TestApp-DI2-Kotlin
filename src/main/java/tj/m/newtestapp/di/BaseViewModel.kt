package tj.m.newtestapp.di

import androidx.lifecycle.ViewModel
import tj.m.newtestapp.ui.gallery.GalleryViewModel
import tj.m.newtestapp.ui.main.MainViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .repositoryModule(RepositoryModule)
        .build()

    init {
        inject()
    }


    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainViewModel -> injector.inject(this)
            is GalleryViewModel -> injector.inject(this)
        }
    }

}
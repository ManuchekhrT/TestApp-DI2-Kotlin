package tj.m.newtestapp.di

import dagger.Component
import tj.m.newtestapp.ui.gallery.GalleryViewModel
import tj.m.newtestapp.ui.main.MainViewModel
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(RepositoryModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified HomeViewModel.
     * @param mainViewModel MainViewModel in which to inject the dependencies
     */
    fun inject(mainViewModel: MainViewModel)
    fun inject(galleryViewModel: GalleryViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun repositoryModule(repositoryModule: RepositoryModule): Builder
    }
}
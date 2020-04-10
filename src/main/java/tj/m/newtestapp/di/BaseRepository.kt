package tj.m.newtestapp.di

import tj.m.newtestapp.data.repository.PostRepository

abstract class BaseRepository {

    private val injector: RepositoryInjector = DaggerRepositoryInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostRepository -> injector.inject(this)
        }
    }
}
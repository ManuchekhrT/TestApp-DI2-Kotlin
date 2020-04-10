package tj.m.newtestapp.di

import dagger.Component
import tj.m.newtestapp.data.repository.PostRepository
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface RepositoryInjector {
    /**
     * Injects required dependencies into the specified AuthRepository.
     * @param postRepository PostRepository in which to inject the dependenciesд
     */
    fun inject(postRepository: PostRepository)


    @Component.Builder
    interface Builder {
        fun build(): RepositoryInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
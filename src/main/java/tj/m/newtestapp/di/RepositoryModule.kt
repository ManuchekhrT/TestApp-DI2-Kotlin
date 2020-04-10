package tj.m.newtestapp.di

import dagger.Module
import dagger.Provides
import tj.m.newtestapp.data.repository.PostRepository

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object RepositoryModule {

    @Provides
    internal fun providePostRepository(): PostRepository {
        return PostRepository()
    }

}
package tj.m.newtestapp.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import tj.m.newtestapp.data.repository.PostApi
import tj.m.newtestapp.networking.ApiClient


/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {

    /**
     * Provides the Auth repository implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Auth repository implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return ApiClient.getApiClient()
    }


}
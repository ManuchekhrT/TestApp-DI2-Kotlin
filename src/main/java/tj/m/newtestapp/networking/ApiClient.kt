package tj.m.newtestapp.networking

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tj.m.newtestapp.networking.ApiEndpoints.API_BASE

object ApiClient {

    private var retrofit: Retrofit? = null
    private const val CLIENT_ID = "b577b695d79c1e1"

    fun getBackgroundImageUrl(imgHash: String): String? =
        "http://i.imgur.com/$imgHash.png"


    fun getApiClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(API_BASE)
                .client(getAuthorizationHeaderInterceptor())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }
        return retrofit!!
    }

    private fun getAuthorizationHeaderInterceptor(): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor { chain ->
            val originalRequest: Request = chain.request()
            val builder: Request.Builder = originalRequest.newBuilder().header(
                "Authorization",
                "Client-ID $CLIENT_ID"
            )
            val newRequest: Request = builder.build()
            chain.proceed(newRequest)
        }.build()
    }

}
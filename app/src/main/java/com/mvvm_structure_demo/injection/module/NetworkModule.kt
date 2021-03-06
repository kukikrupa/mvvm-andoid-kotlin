package com.mvvm_structure_demo.injection.module

import com.mvvm_structure_demo.BuildConfig
import com.mvvm_structure_demo.network.PostApi
import com.mvvm_structure_demo.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
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

        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        else
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)

        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()

            var token = ""
            /*MainActivity.prefs!!.userDataModel?.let {
                token= it.data.access_token
            }*/

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .header(
                    "Authorization",
                    "Bearer " + token) // <-- this is the important line

            val request = requestBuilder.build()
            chain.proceed(request)
        }.addInterceptor(logging).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}
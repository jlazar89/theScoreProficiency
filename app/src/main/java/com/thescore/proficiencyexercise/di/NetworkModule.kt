package com.thescore.proficiencyexercise.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.thescore.proficiencyexercise.data.prefs.AppPrefsHelper
import com.thescore.proficiencyexercise.utils.AppUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

/**
 * Created Date: 10/27/2020
 * Purpose: KOIN Module for managing the Networking related Dependencies
 */

val networkModule = module {

    single { provideGson() }
    single { provideCache(androidContext()) }
    single { provideHttpClient(androidContext(), get(), get()) }

}

// This variable define 10 MB of cache size.
const val cacheSize = (10 * 1024 * 1024).toLong()

/**
 *  This method will provide the instance of Gson.
 */
fun provideGson(): Gson {
    return GsonBuilder()
        .create()
}

/**
 * This method will provide the cache instance with size of 10 MB
 */
fun provideCache(context: Context): Cache = Cache(context.cacheDir, cacheSize)


/**
 *  this method will provide the okhttpclient instance for API.
 */
fun provideHttpClient(context: Context, cache: Cache, prefs: AppPrefsHelper): OkHttpClient {

    return OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor { chain ->
            val request = chain.request()
            val requestBuilder = request.newBuilder()

            // SET CACHE MECHANISM HERE.
            if (AppUtils.hasNetwork(context)) {
                /*
                       *  If there is Internet, get the cache that was stored 5 seconds ago.
                       *  If the cache is older than 5 seconds, then discard it,
                       *  and indicate an error in fetching the response.
                       *  The 'max-age' attribute is responsible for this behavior.
                       */
                requestBuilder.header("Cache-Control", "public, max-age=" + 5)
            } else {
                /*
                 *  If there is no Internet, get the cache that was stored 7 days ago.
                 *  If the cache is older than 7 days, then discard it,
                 *  and indicate an error in fetching the response.
                 *  The 'max-stale' attribute is responsible for this behavior.
                 *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                 */
                requestBuilder.header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                )
            }
            return@addInterceptor chain.proceed(requestBuilder.build())
        }
        .addInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .hostnameVerifier { _, _ -> true }
        .build()
}


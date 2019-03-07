package com.quaksire.network

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


/**
 * Created by Julio.
 */
@Module
class NetworkModule (private val mUrl: String) {

    @Provides
    fun provideService(retrofit: Retrofit) : JsonPlaceholderService {
        return retrofit.create(JsonPlaceholderService::class.java)
    }

    /**
     * Create new Retrofit client
     * @param client Http client
     * @param gsonConverterFactory Json converter to model
     * @param rxJavaCallAdapterFactory rxJava adapter
     * @return Retrofit client
     */
    @Provides
    fun provideRetrofit(
        client: OkHttpClient, gsonConverterFactory: GsonConverterFactory,
        rxJavaCallAdapterFactory: RxJavaCallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(this.mUrl)
            .addCallAdapterFactory(rxJavaCallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    /**
     * Create Http client with the next specifications
     * Connection timeout: 30 seconds
     * Read timeout: 5 seconds
     * Write timeout: 5 seconds
     * @return Http client
     */
    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Create new RxJavaCallAdapter
     * @return RxJavaCallAdapter instance
     */
    @Provides
    fun provideRxJavaCallAdapterFactory(): RxJavaCallAdapterFactory {
        return RxJavaCallAdapterFactory.create()
    }

    /**
     * Create new GsonConverterFactory
     * @param gson Gson instance
     * @return new GsonConverterFactory
     */
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    /**
     * Create new Gson object to be injected
     * @return new Gson instance
     */
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}
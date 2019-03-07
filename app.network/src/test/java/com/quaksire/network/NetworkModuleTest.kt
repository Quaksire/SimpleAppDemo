package com.quaksire.network

import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Julio.
 */
class NetworkModuleTest {
    private lateinit var mNetworkModule: NetworkModule

    @Before
    fun setUp() {
        this.mNetworkModule = NetworkModule("http://test")
    }

    @Test
    fun canProvideGson() {
        Assert.assertNotNull(this.mNetworkModule.provideGson())
    }

    @Test
    fun canProvideRxJavaCallAdapterFactory() {
        Assert.assertNotNull(this.mNetworkModule.provideRxJavaCallAdapterFactory())
    }

    @Test
    fun canProvideGsonConverterFactory() {
        Assert.assertNotNull(
            this.mNetworkModule.provideGsonConverterFactory(
                this.mNetworkModule.provideGson()
            )
        )
    }

    @Test
    fun canProvideClient() {
        Assert.assertNotNull(this.mNetworkModule.provideClient())
    }

    @Test
    fun canProvideRetrofit() {
        Assert.assertNotNull(
            this.mNetworkModule.provideRetrofit(
                this.mNetworkModule.provideClient(),
                this.mNetworkModule.provideGsonConverterFactory(
                    this.mNetworkModule.provideGson()
                ),
                this.mNetworkModule.provideRxJavaCallAdapterFactory()
            )
        )
    }
}
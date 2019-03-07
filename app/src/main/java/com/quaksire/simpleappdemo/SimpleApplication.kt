package com.quaksire.simpleappdemo

import android.app.Application
import com.quaksire.network.NetworkModule
import com.quaksire.network.Endpoint
import com.quaksire.simpleappdemo.di.*

/**
 * Created by Julio.
 */
class SimpleApplication: Application() {

    fun getActivityComponent(activity: IListPostActivity) : ActivityComponent {
        return DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(activity))
            .retrofitComponent(getRetrofitComponent())
            .build()
    }

    fun getPostActivityComponent(activity: IPostActivity) : PostActivityComponent {
        return DaggerPostActivityComponent
            .builder()
            .postActivityModule(PostActivityModule(activity))
            .retrofitComponent(getRetrofitComponent())
            .build()
    }

    private fun getRetrofitComponent() : RetrofitComponent {
        Endpoint.baseUrl = BuildConfig.BASE_URL

        return DaggerRetrofitComponent
            .builder()
            .networkModule(NetworkModule(Endpoint.baseUrl))
            .build()
    }
}
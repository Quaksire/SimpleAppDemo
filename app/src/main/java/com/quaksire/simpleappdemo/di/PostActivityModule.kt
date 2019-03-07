package com.quaksire.simpleappdemo.di

import com.quaksire.network.JsonPlaceholderService
import com.quaksire.simpleappdemo.IPostActivity
import com.quaksire.simpleappdemo.presenters.PostPresenter
import com.quaksire.simpleappdemo.presenters.PostPresenterImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Julio.
 */
@Module
class PostActivityModule(private val postActivity: IPostActivity) {

    @Provides
    fun providePostPresenter(service: JsonPlaceholderService) : PostPresenter {
        return PostPresenterImpl(service, postActivity)
    }
}
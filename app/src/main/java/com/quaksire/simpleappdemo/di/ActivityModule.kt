package com.quaksire.simpleappdemo.di

import com.quaksire.network.JsonPlaceholderService
import com.quaksire.simpleappdemo.IListPostActivity
import com.quaksire.simpleappdemo.presenters.ListPostPresenter
import com.quaksire.simpleappdemo.presenters.ListPostPresenterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Julio.
 */
@Module
class ActivityModule(private val listPostActivity: IListPostActivity) {

    @Provides
    fun provideListPostPresenter(service: JsonPlaceholderService) : ListPostPresenter {
        return ListPostPresenterImpl(service, listPostActivity)
    }
}

package com.quaksire.simpleappdemo.di

import com.quaksire.simpleappdemo.ListPostActivity
import dagger.Component

/**
 * Created by Julio.
 */
@Component(
    dependencies = [RetrofitComponent::class],
    modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(listPostActivity: ListPostActivity)
}
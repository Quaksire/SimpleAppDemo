package com.quaksire.simpleappdemo.di

import com.quaksire.simpleappdemo.PostActivity
import dagger.Component

/**
 * Created by Julio.
 */
@Component(
    dependencies = [RetrofitComponent::class],
    modules = [PostActivityModule::class])
interface PostActivityComponent {
    fun inject(postActivity: PostActivity)
}
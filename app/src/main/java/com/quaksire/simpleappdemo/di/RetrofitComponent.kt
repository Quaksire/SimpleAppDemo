package com.quaksire.simpleappdemo.di

import com.quaksire.network.JsonPlaceholderService
import com.quaksire.network.NetworkModule
import dagger.Component



/**
 * Created by Julio.
 */
@Component(modules = [NetworkModule::class])
interface RetrofitComponent {
    fun service(): JsonPlaceholderService
}
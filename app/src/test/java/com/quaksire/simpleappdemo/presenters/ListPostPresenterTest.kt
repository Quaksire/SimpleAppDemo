package com.quaksire.simpleappdemo.presenters

import com.quaksire.model.Post
import com.quaksire.network.JsonPlaceholderService
import com.quaksire.simpleappdemo.IListPostActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import rx.Scheduler
import rx.Single
import java.lang.RuntimeException
import rx.android.plugins.RxAndroidSchedulersHook
import rx.android.plugins.RxAndroidPlugins
import rx.schedulers.Schedulers


/**
 * Created by Julio.
 */
@RunWith(MockitoJUnitRunner::class)
class ListPostPresenterTest {

    private lateinit var activity: IListPostActivity
    private lateinit var service: JsonPlaceholderService
    private lateinit var presenter: ListPostPresenter

    @Before
    fun setUp() {
        // Override RxAndroid schedulers
        val rxAndroidPlugins = RxAndroidPlugins.getInstance()
        rxAndroidPlugins.registerSchedulersHook(object : RxAndroidSchedulersHook() {
            override fun getMainThreadScheduler(): Scheduler {
                return Schedulers.immediate()
            }
        })

        activity = Mockito.mock(IListPostActivity::class.java)
        service = Mockito.mock(JsonPlaceholderService::class.java)
        presenter = ListPostPresenterImpl(service, activity)
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.getInstance().reset()
    }

    @Test
    fun canShowPostIfRequestIsSuccessful() {
        val listOfPost = ArrayList<Post>()
        Mockito.`when`(service.getPost()).thenReturn(Single.just(listOfPost))
        presenter.requestListOfPost()
        Mockito.verify(activity).showPosts(listOfPost)
    }

    @Test
    fun canShowErrorIfRequestFail() {
        Mockito.`when`(service.getPost()).thenReturn(Single.error(RuntimeException()))
        presenter.requestListOfPost()
        Mockito.verify(activity).showError()
    }


}
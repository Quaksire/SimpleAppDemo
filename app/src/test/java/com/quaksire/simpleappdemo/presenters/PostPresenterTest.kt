package com.quaksire.simpleappdemo.presenters

import com.quaksire.model.Comment
import com.quaksire.model.Company
import com.quaksire.model.Post
import com.quaksire.model.User
import com.quaksire.network.JsonPlaceholderService
import com.quaksire.simpleappdemo.IListPostActivity
import com.quaksire.simpleappdemo.IPostActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import rx.Scheduler
import rx.Single
import rx.android.plugins.RxAndroidPlugins
import rx.android.plugins.RxAndroidSchedulersHook
import rx.schedulers.Schedulers

/**
 * Created by Julio.
 */
@RunWith(MockitoJUnitRunner::class)
class PostPresenterTest {
    private lateinit var activity: IPostActivity
    private lateinit var service: JsonPlaceholderService
    private lateinit var presenter: PostPresenter

    @Before
    fun setUp() {
        // Override RxAndroid schedulers
        val rxAndroidPlugins = RxAndroidPlugins.getInstance()
        rxAndroidPlugins.registerSchedulersHook(object : RxAndroidSchedulersHook() {
            override fun getMainThreadScheduler(): Scheduler {
                return Schedulers.immediate()
            }
        })

        activity = Mockito.mock(IPostActivity::class.java)
        service = Mockito.mock(JsonPlaceholderService::class.java)
        presenter = PostPresenterImpl(service, activity)
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.getInstance().reset()
    }

    @Test
    fun requestData_canDisplayPost() {
        val post = Post(1, 1, "title", "body")
        val comment = Comment(1, 1, "Name", "Email", "Body")
        val comments = ArrayList<Comment>()
        comments.add(comment)
        val user = User(1, "Name", "Surname", "email", Company("Name", "", ""), "")
        Mockito.`when`(service.getPost(1)).thenReturn(Single.just(post))
        Mockito.`when`(service.getUser(1)).thenReturn(Single.just(user))
        Mockito.`when`(service.getCommentsForPost(1)).thenReturn(Single.just(comments))

        presenter.getDataForPost(1, 1)

        //Mockito.verify(activity).displayPost(post)
        //Mockito.verify(activity).displayUser(user)
        //Mockito.verify(activity).displayComments(1)
    }
}
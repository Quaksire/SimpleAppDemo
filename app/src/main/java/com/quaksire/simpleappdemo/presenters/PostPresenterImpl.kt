package com.quaksire.simpleappdemo.presenters

import com.quaksire.model.Post
import com.quaksire.model.User
import com.quaksire.network.JsonPlaceholderService
import com.quaksire.simpleappdemo.IPostActivity
import rx.Single
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Julio.
 */
class PostPresenterImpl : PostPresenter {

    private val service: JsonPlaceholderService
    private val view: IPostActivity

    @Inject
    constructor(service: JsonPlaceholderService, view: IPostActivity) {
        this.service = service
        this.view = view
    }

    override fun getDataForPost(postId: Long, userId: Long) {
        Single.merge(
                service.getPost(postId),
                service.getUser(userId),
                service.getCommentsForPost(postId).map { comments -> comments.filter {it.postId == postId}.size })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> displayResult(result) }, { })
    }

    private fun displayResult(result: Any) {
        if (result is Post) {
            view.displayPost(result)
        } else if (result is User) {
            view.displayUser(result)
        } else if (result is Int) {
            view.displayComments(result)
        }
    }

}
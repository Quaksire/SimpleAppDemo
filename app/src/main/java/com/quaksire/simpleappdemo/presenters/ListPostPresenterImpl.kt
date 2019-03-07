package com.quaksire.simpleappdemo.presenters

import com.quaksire.model.Post
import com.quaksire.network.JsonPlaceholderService
import com.quaksire.simpleappdemo.IListPostActivity
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Julio.
 */
class ListPostPresenterImpl : ListPostPresenter {

    private val service: JsonPlaceholderService
    private val view: IListPostActivity

    @Inject
    constructor(service: JsonPlaceholderService, view: IListPostActivity) {
        this.service = service
        this.view = view
    }

    override fun requestListOfPost() {

        service.getPost()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ posts -> showPost(posts) }, { showError() })
    }

    private fun showPost(posts : List<Post>) {
        view.showPosts(posts)
    }

    private fun showError() {
        view.showError()
    }

}
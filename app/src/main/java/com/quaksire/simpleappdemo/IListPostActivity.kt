package com.quaksire.simpleappdemo

import com.quaksire.model.Post

/**
 * Created by Julio.
 */
interface IListPostActivity {

    fun showError()
    fun showPosts(post : List<Post>)
}
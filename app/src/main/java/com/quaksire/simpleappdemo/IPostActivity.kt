package com.quaksire.simpleappdemo

import com.quaksire.model.Post
import com.quaksire.model.User

/**
 * Created by Julio.
 */
interface IPostActivity {

    fun displayPost(post: Post)
    fun displayUser(user: User)
    fun displayComments(comments: Int)
}
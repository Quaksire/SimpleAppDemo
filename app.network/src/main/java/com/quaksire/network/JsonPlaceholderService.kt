package com.quaksire.network

import com.quaksire.model.Comment
import com.quaksire.model.Post
import com.quaksire.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Single

/**
 * Created by Julio.
 */
interface JsonPlaceholderService {
    @GET("posts")
    fun getPost() : Single<List<Post>>

    @GET("posts/{postId}")
    fun getPost(@Path("postId") postId: Long) : Single<Post>

    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Long) : Single<User>

    @GET("posts/{postId}/comments")
    fun getCommentsForPost(@Path("postId") postId: Long) : Single<List<Comment>>
}
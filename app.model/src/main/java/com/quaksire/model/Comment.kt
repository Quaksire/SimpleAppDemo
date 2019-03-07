package com.quaksire.model

/**
 * Created by Julio.
 */
data class Comment (
    val postId: Long,
    val id: Long,
    var name: String,
    val email: String,
    val body: String)
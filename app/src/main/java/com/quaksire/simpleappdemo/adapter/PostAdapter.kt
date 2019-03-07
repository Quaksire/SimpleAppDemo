package com.quaksire.simpleappdemo.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.quaksire.model.Post
import android.support.design.widget.CoordinatorLayout.Behavior.setTag
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.quaksire.simpleappdemo.PostActivity
import com.quaksire.simpleappdemo.R


/**
 * Created by Julio.
 */
class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private lateinit var posts : List<Post>

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        try {
            val post = posts[position]

            holder.view.tag = post
            holder.title.text = post.title
            holder.id.text = "" + post.id

            holder.view.setOnClickListener { view -> startPostActivity(view.context, view.tag as Post) }

        } catch (e: Exception) {
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): PostViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.content_post, viewGroup, false)

        return PostViewHolder(itemView)
    }

    fun swapData(posts: List<Post>) {
        this.posts = posts
    }

    private fun startPostActivity(context: Context, post: Post) {
        val postActivityIntent = Intent(context, PostActivity::class.java)
        postActivityIntent.putExtra("POST_ID", post.id)
        postActivityIntent.putExtra("USER_ID", post.userId)
        context.startActivity(postActivityIntent)
    }

}
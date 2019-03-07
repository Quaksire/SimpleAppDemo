package com.quaksire.simpleappdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.quaksire.model.Post
import com.quaksire.model.User
import com.quaksire.simpleappdemo.presenters.PostPresenter

import javax.inject.Inject

class PostActivity : AppCompatActivity(), IPostActivity {

    @Inject
    lateinit var presenter: PostPresenter

    private lateinit var postTitleTextView : TextView
    private lateinit var postBodyTextView : TextView
    private lateinit var authorTextView : TextView
    private lateinit var commentsTextView : TextView

    private var postId: Long = 0
    private var userId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        (application as SimpleApplication)
            .getPostActivityComponent(this)
            .inject(this)

        this.postId = intent.getLongExtra("POST_ID", 0)
        this.userId = intent.getLongExtra("USER_ID", 0)

        this.postTitleTextView = findViewById(R.id.postTitle)
        this.postBodyTextView = findViewById(R.id.postBody)
        this.authorTextView = findViewById(R.id.author)
        this.commentsTextView = findViewById(R.id.comments)
    }

    override fun onResume() {
        super.onResume()
        presenter.getDataForPost(postId, userId)
    }

    override fun displayPost(post: Post) {
        this.postTitleTextView.text = post.title
        this.postBodyTextView.text = post.body
    }

    override fun displayUser(user: User) {
        this.authorTextView.text = user.name
    }

    override fun displayComments(comments: Int) {
        this.commentsTextView.text = "" + comments
    }

}

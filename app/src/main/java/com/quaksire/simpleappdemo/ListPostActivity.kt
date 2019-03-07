package com.quaksire.simpleappdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import com.quaksire.model.Post
import com.quaksire.simpleappdemo.adapter.PostAdapter
import com.quaksire.simpleappdemo.presenters.ListPostPresenter
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import javax.inject.Inject


class ListPostActivity : AppCompatActivity(), IListPostActivity, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: ListPostPresenter
    private lateinit var adapter: PostAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeContainer: SwipeRefreshLayout
    private lateinit var message: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_post)

        (application as SimpleApplication)
            .getActivityComponent(this)
            .inject(this)

        this.message = findViewById(R.id.message)
        this.recyclerView = findViewById(R.id.recyclerView)
        this.swipeContainer = findViewById(R.id.swipeContainer)

        swipeContainer.setOnRefreshListener(this)
        swipeContainer.setColorSchemeResources(R.color.colorPrimary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark)

        this.adapter = PostAdapter()
        val layoutManager = LinearLayoutManager(this)
        this.recyclerView.layoutManager = layoutManager
        this.recyclerView.itemAnimator = DefaultItemAnimator()
        this.recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.requestListOfPost()

        this.message.visibility = View.VISIBLE
        this.swipeContainer.visibility = View.GONE

        this.message.text = getText(R.string.list_loading)
    }

    //===========================================
    // IListPostActivity
    //===========================================
    override fun showError() {
        this.message.visibility = View.VISIBLE
        this.swipeContainer.visibility = View.GONE

        this.message.text = getText(R.string.list_error)
    }

    override fun showPosts(post: List<Post>) {
        this.swipeContainer.isRefreshing = false

        this.message.visibility = View.GONE
        this.swipeContainer.visibility = View.VISIBLE

        adapter.swapData(post)
        adapter.notifyDataSetChanged()
    }

    //===========================================
    // SwipeRefreshLayout.OnRefreshListener
    //===========================================
    override fun onRefresh() {
        this.message.visibility = View.VISIBLE
        this.swipeContainer.visibility = View.GONE

        this.message.text = getText(R.string.list_loading)

        this.presenter.requestListOfPost()
    }
}

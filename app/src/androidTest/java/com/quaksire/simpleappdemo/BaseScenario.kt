package com.quaksire.simpleappdemo

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.IdlingPolicies
import com.quaksire.network.Endpoint
import com.quaksire.simpleappdemo.utils.RestServiceTestHelper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * Created by Julio.
 */
open class BaseScenario {

    val COMMENTS = "response_comments_200.json"
    val POSTS = "response_posts_200.json"
    val USERS = "response_users_200.json"
    val USER = "response_user_200.json"
    val POST = "response_post_200.json"

    /**
     * MockWebServer - Test will request all information from a mock server
     */
    private var server: MockWebServer? = null

    @Before
    @Throws(IOException::class)
    fun setUp() {
        IdlingPolicies.setMasterPolicyTimeout(60, TimeUnit.SECONDS)
        IdlingPolicies.setIdlingResourceTimeout(26, TimeUnit.SECONDS)

        this.server = MockWebServer()
        this.server!!.start()
        Endpoint.baseUrl = this.server!!.url("/").toString()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        server!!.shutdown()
    }

    //=======================================================
    // Server methods
    //=======================================================

    @Throws(Exception::class)
    protected fun setResponse200ToServer(filename: String, code: Int) {
        this.server!!.enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(
                    RestServiceTestHelper.getStringFromFile(
                        getInstrumentation().context,
                        filename
                    )
                )
        )
    }
}
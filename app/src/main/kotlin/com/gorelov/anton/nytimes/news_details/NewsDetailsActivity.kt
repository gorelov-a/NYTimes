package com.gorelov.anton.nytimes.news_details

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.gorelov.anton.nytimes.R


class NewsDetailsActivity : MvpAppCompatActivity() {

    companion object {
        private val BUNDLE_KEY_NEWS_ID = "newsId"

        fun start(activity: MvpAppCompatActivity, id: Int) = with(Intent(activity, NewsDetailsActivity::class.java)) {
            putExtra(BUNDLE_KEY_NEWS_ID, id)
            activity.startActivity(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
    }
}

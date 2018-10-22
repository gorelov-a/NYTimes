package com.gorelov.anton.nytimes.news_details

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.common.BaseActivity
import com.gorelov.anton.nytimes.di.DI
import com.gorelov.anton.nytimes.model.NewsItemId
import kotlinx.android.synthetic.main.activity_news_details.*
import toothpick.Scope


class NewsDetailsActivity : BaseActivity(), NewsDetailsView {

    companion object {
        private val BUNDLE_KEY_NEWS_ID = "newsId"

        fun start(activity: MvpAppCompatActivity, url: String) = with(Intent(activity, NewsDetailsActivity::class.java)) {
            putExtra(BUNDLE_KEY_NEWS_ID, url)
            activity.startActivity(this)
        }
    }

    private lateinit var scope: Scope

    @InjectPresenter
    lateinit var newsDetailsPresenter: NewsDetailsPresenter

    @ProvidePresenter
    fun provideDetailsPresenter(): NewsDetailsPresenter = scope.getInstance(NewsDetailsPresenter::class.java)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        scope = DI.openNewsDetailsScope(NewsItemId(intent.getStringExtra(BUNDLE_KEY_NEWS_ID)), savedInstanceState == null)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        webView.settings.javaScriptEnabled = true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun openUrl(url: String) = webView.loadUrl(url)
}

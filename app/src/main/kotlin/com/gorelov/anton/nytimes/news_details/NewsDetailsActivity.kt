package com.gorelov.anton.nytimes.news_details

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.di.DI
import kotlinx.android.synthetic.main.activity_news_details.*


class NewsDetailsActivity : MvpAppCompatActivity(), NewsDetailsView {

    companion object {
        private val BUNDLE_KEY_NEWS_ID = "newsId"

        fun start(activity: MvpAppCompatActivity, id: Int) = with(Intent(activity, NewsDetailsActivity::class.java)) {
            putExtra(BUNDLE_KEY_NEWS_ID, id)
            activity.startActivity(this)
        }
    }

    private val scope by lazy { DI.openNewsDetailsScope(getIntent().getIntExtra(BUNDLE_KEY_NEWS_ID, -1)) }

    @InjectPresenter
    lateinit var newsDetailsPresenter: NewsDetailsPresenter

    @ProvidePresenter
    fun provideDetailsPresenter(): NewsDetailsPresenter = scope.getInstance(NewsDetailsPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun setActionBarCategory(category: String) {
        supportActionBar?.apply {
            title = category
        }
    }

    override fun setTitle(title: String) {
        news_details_title.text = title
    }

    override fun setContent(content: String) {
        news_details_content.text = content
    }

    override fun setDate(string: String) {
        news_details_date.text = string
    }

    override fun setHeadingImage(drawable: Drawable) {
        news_details_heading_image.setImageDrawable(drawable)
    }
}

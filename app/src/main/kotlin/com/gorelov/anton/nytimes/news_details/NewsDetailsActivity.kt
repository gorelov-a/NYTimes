package com.gorelov.anton.nytimes.news_details

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.common.BaseActivity
import com.gorelov.anton.nytimes.di.DI
import com.gorelov.anton.nytimes.model.NewsItemId
import com.gorelov.anton.nytimes.news_details.vm.NewsDetailsItemVM
import kotlinx.android.synthetic.main.activity_news_details.*
import toothpick.Scope


class NewsDetailsActivity : BaseActivity(), NewsDetailsView {

    companion object {
        private val BUNDLE_KEY_NEWS_ID = "newsId"

        fun start(activity: MvpAppCompatActivity, id: Int) = with(Intent(activity, NewsDetailsActivity::class.java)) {
            putExtra(BUNDLE_KEY_NEWS_ID, id)
            activity.startActivity(this)
        }
    }

    private lateinit var scope: Scope

    @InjectPresenter
    lateinit var newsDetailsPresenter: NewsDetailsPresenter

    @ProvidePresenter
    fun provideDetailsPresenter(): NewsDetailsPresenter = scope.getInstance(NewsDetailsPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        scope = DI.openNewsDetailsScope(NewsItemId(intent.getIntExtra(BUNDLE_KEY_NEWS_ID, -1)), savedInstanceState == null)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showNewsItem(newsDetailsItemVM: NewsDetailsItemVM) {
        with(newsDetailsItemVM) {
            supportActionBar?.apply {
                title = category
            }

            news_details_title.text = title
            news_details_content.text = content
            news_details_date.text = publishDate
            Glide.with(this@NewsDetailsActivity).applyDefaultRequestOptions(RequestOptions().fitCenter()).load(imageUrl).into(news_details_heading_image)
        }
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }
}

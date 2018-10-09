package com.gorelov.anton.nytimes.news_details

import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.di.DI
import com.gorelov.anton.nytimes.model.NewsItemId
import com.gorelov.anton.nytimes.news_details.vm.NewsItemVM
import kotlinx.android.synthetic.main.activity_news_details.*


class NewsDetailsActivity : MvpAppCompatActivity(), NewsDetailsView {

    companion object {
        private val BUNDLE_KEY_NEWS_ID = "newsId"

        fun start(activity: MvpAppCompatActivity, id: Int) = with(Intent(activity, NewsDetailsActivity::class.java)) {
            putExtra(BUNDLE_KEY_NEWS_ID, id)
            activity.startActivity(this)
        }
    }

    private val scope by lazy { DI.openNewsDetailsScope(NewsItemId(intent.getIntExtra(BUNDLE_KEY_NEWS_ID, -1))) }

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

    override fun showNewsItem(newsItemVM: NewsItemVM) {
        with(newsItemVM) {
            supportActionBar?.apply {
                title = category
            }

            news_details_title.text = title
            news_details_content.text = content
            news_details_date.text = publishDate
            Glide.with(this@NewsDetailsActivity).applyDefaultRequestOptions(RequestOptions().centerCrop()).load(imageUrl).into(news_details_heading_image)
        }
    }

    override fun showToast(@StringRes stringId: Int) = Toast.makeText(baseContext, stringId, Toast.LENGTH_LONG).show()
}

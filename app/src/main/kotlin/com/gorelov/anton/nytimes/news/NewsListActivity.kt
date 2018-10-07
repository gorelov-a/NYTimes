package com.gorelov.anton.nytimes.news

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.about.AboutActivity
import com.gorelov.anton.nytimes.di.DI
import com.gorelov.anton.nytimes.news.model.NewsItem
import kotlinx.android.synthetic.main.activity_news_list.*


class NewsListActivity : MvpAppCompatActivity(), NewsListView {

    private val scope by lazy { DI.openNewsListScope() }

    @InjectPresenter
    lateinit var newsListPresenter: NewsListPresenter

    @ProvidePresenter
    fun provideNewsListPresenter(): NewsListPresenter = scope.getInstance(NewsListPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_news_list)


        news_list.adapter = NewsListAdapter(this, newsListPresenter.getNewList(), object : NewsListAdapter.OnItemClickListener {
            override fun onItemClick(newsItem: NewsItem) {

            }
        })
        when {
            resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT -> news_list.layoutManager = LinearLayoutManager(this)
            else -> news_list.layoutManager = GridLayoutManager(this, NewsListConsts.landscapeNewsColumnsCount)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
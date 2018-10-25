package com.gorelov.anton.nytimes.news

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.about.AboutActivity
import com.gorelov.anton.nytimes.common.BaseActivity
import com.gorelov.anton.nytimes.common.SpacesItemDecoration
import com.gorelov.anton.nytimes.di.DI
import com.gorelov.anton.nytimes.news.vm.NewsListItemVM
import com.gorelov.anton.nytimes.news_details.NewsDetailsActivity
import kotlinx.android.synthetic.main.activity_news_list.*


class NewsListActivity : BaseActivity(), NewsListView, OnItemSelectedListener {

    private val scope by lazy { DI.openNewsListScope() }

    @InjectPresenter
    lateinit var newsListPresenter: NewsListPresenter

    @ProvidePresenter
    fun provideNewsListPresenter(): NewsListPresenter = scope.getInstance(NewsListPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        when {
            resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT -> news_list.layoutManager = LinearLayoutManager(this)
            else -> news_list.layoutManager = GridLayoutManager(this, NewsListConsts.landscapeNewsColumnsCount)
        }
        news_list.addItemDecoration(SpacesItemDecoration(this, R.dimen.news_card_between_space))
        swipe_refresh_layout.setOnRefreshListener {
            newsListPresenter.onRefresh()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)

        val item = menu.findItem(R.id.spinner)
        val spinner = item.actionView as Spinner
        spinner.onItemSelectedListener = this

        val adapter = ArrayAdapter.createFromResource(this, R.array.categories, R.layout.category_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
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

    override fun showNews(news: List<NewsListItemVM>) {
        news_list.adapter = NewsListAdapter(this, news) {
            NewsDetailsActivity.start(this@NewsListActivity, it.id)
        }
    }

    override fun setRefreshing(isRefreshing: Boolean) {
        swipe_refresh_layout.isRefreshing = isRefreshing
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        newsListPresenter.onCategoryItemClick(position)
    }

}
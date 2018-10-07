package com.gorelov.anton.nytimes.news

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.about.AboutActivity
import com.gorelov.anton.nytimes.common.DataUtils
import com.gorelov.anton.nytimes.di.DI
import kotlinx.android.synthetic.main.activity_news_list.*


class NewsListActivity : MvpAppCompatActivity(), NewsListView {

    private val scope by lazy { DI.openNewsListScope() }
    private lateinit var adapter: NewsListAdapter

    @InjectPresenter
    lateinit var newsListPresenter: NewsListPresenter

    @ProvidePresenterTag(presenterClass = NewsListPresenter::class)
    fun provideDialogPresenterTag(): String = "NewListPresenter"

    @ProvidePresenter
    fun provideNewsListPresenter(): NewsListPresenter = scope.getInstance(NewsListPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_news_list)


        adapter = NewsListAdapter(this, DataUtils.generateActors())
        news_list.setAdapter(adapter)
        news_list.setOnItemClickListener(clickListener)
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
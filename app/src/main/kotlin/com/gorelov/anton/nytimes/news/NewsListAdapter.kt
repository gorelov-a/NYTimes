package com.gorelov.anton.nytimes.news

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.news.model.NewsItem


class NewsListAdapter(context: Context, val news: List<NewsItem>, val clickListener: AdapterView.OnItemClickListener?) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val imageLoader: RequestManager;

    init {
        val imageOption = RequestOptions().centerCrop()
        this.imageLoader = Glide.with(context).applyDefaultRequestOptions(imageOption)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        if (convertView == null) {
            holder = onCreateViewHolder(parent)
            holder.itemView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        onBindViewHolder(holder, position)
        return holder.itemView
    }

    private fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = news[position]

        imageLoader.load(item.imageUrl).into(holder.headingImage)
        holder.category.text = item.category.name
        holder.title.text = item.title
        holder.previewText.text = item.previewText

    }

    private fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.view_news_item, parent, false))
    }

    override fun getItem(position: Int): Any = news[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = news.size

    class ViewHolder constructor(val itemView: View, listener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener(listener)
        }

        fun bind(item: NewsItem) {
            imageLoader.load(item.imageUrl).into(holder.headingImage)
            category.text = item.category.name
            title.text = item.title
            previewText.text = item.previewText
        }
    }
}
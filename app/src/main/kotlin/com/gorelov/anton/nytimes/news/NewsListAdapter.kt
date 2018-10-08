package com.gorelov.anton.nytimes.news

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.model.NewsItem


class NewsListAdapter(context: Context, private val news: List<NewsItem>, private val clickListener: OnItemClickListener?) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val imageLoader: RequestManager = Glide.with(context).applyDefaultRequestOptions(RequestOptions().centerCrop());


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.view_news_item, parent, false), clickListener)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    interface OnItemClickListener {
        fun onItemClick(newsItem: NewsItem)
    }

    inner class ViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        private val category: TextView = itemView.findViewById(R.id.news_list_category)
        private val title: TextView = itemView.findViewById(R.id.news_list_title)
        private val previewText: TextView = itemView.findViewById(R.id.news_list_preview_text)
        private val date: TextView = itemView.findViewById(R.id.news_list_date)
        private val headingImage: ImageView = itemView.findViewById(R.id.news_list_heading_image)

        init {
            itemView.setOnClickListener {
                if (listener != null && adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onItemClick(news[adapterPosition])
                }
            }
        }

        fun bind(newsItem: NewsItem) {
            imageLoader.load(newsItem.imageUrl).into(headingImage)
            category.text = newsItem.category.name
            title.text = newsItem.title
            previewText.text = newsItem.previewText
            date.text = DateUtils.getRelativeDateTimeString(
                    date.context,
                    newsItem.publishDate.time,
                    DateUtils.MINUTE_IN_MILLIS,
                    DateUtils.DAY_IN_MILLIS,
                    DateUtils.FORMAT_SHOW_TIME
            )
        }
    }
}
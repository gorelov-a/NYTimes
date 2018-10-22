package com.gorelov.anton.nytimes.news

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.gorelov.anton.nytimes.R
import com.gorelov.anton.nytimes.news.vm.NewsListItemVM


class NewsListAdapter(context: Context, private val news: List<NewsListItemVM>, private val clickListener: (data: NewsListItemVM) -> Unit) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val imageLoader: RequestManager = Glide.with(context).applyDefaultRequestOptions(RequestOptions().fitCenter());


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.view_news_item, parent, false), clickListener)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    inner class ViewHolder(itemView: View, listener: (data: NewsListItemVM) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val category: TextView = itemView.findViewById(R.id.news_list_category)
        private val title: TextView = itemView.findViewById(R.id.news_list_title)
        private val previewText: TextView = itemView.findViewById(R.id.news_list_preview_text)
        private val date: TextView = itemView.findViewById(R.id.news_list_date)
        private val headingImage: ImageView = itemView.findViewById(R.id.news_list_heading_image)

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.invoke(news[adapterPosition])
                }
            }
        }

        fun bind(newsItem: NewsListItemVM) {
            if (newsItem.imageUrl == null) {
                headingImage.visibility = View.GONE
            } else {
                headingImage.visibility = View.VISIBLE
                imageLoader.load(newsItem.imageUrl).into(headingImage)
            }
            bindTextView(category, newsItem.category)
            bindTextView(title, newsItem.title)
            bindTextView(previewText, newsItem.previewText)
            bindTextView(date, newsItem.publishDate.toString())
        }

        private fun bindTextView(textView: TextView, text: String?) {
            if (text.isNullOrBlank()) {
                textView.visibility = View.GONE
            } else {
                textView.text = text
                textView.visibility = View.VISIBLE
            }
        }
    }
}
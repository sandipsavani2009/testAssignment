package com.test.byju.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.byju.R
import com.test.byju.glide.setUrl
import com.test.byju.io.dto.Article
import com.test.byju.utils.formatToDateStr
import kotlinx.android.synthetic.main.news_headlines_adapter_item.view.*

class NewsHeadlinesAdapter(val context: Context,
                           val articles: List<Article?>? = null,
                           val itemClickListener: (article: Article?) -> Unit) : RecyclerView.Adapter<NewsHeadlinesAdapter.NewsHeadlineHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHeadlineHolder {
        return NewsHeadlineHolder(LayoutInflater.from(context).inflate(R.layout.news_headlines_adapter_item, parent, false))
    }

    override fun getItemCount(): Int { return articles?.size ?: 0 }

    override fun onBindViewHolder(holder: NewsHeadlineHolder, position: Int) {
        val article = articles?.get(position)

        article?.let {
            holder.rootView.news_bg_imgView.setUrl(it.urlToImage)
            holder.rootView.news_source_textView.text = it.source?.name ?: ""
            holder.rootView.news_publish_time_textView.text = it.publishedAt.formatToDateStr()
            holder.rootView.news_title_textView.text = it.title
        }
    }

    /**
     * View holder class
     */
    inner class NewsHeadlineHolder(var rootView: View) : RecyclerView.ViewHolder(rootView) {
        init {
            rootView.news_root_view.setOnClickListener { itemClickListener(articles?.get(adapterPosition)) }
        }
    }
}
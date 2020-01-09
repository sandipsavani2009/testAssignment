package com.test.byju.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.ViewModelProviders
import com.test.byju.R
import com.test.byju.glide.setUrl
import com.test.byju.io.Constants
import com.test.byju.io.dto.Article
import com.test.byju.utils.formatToDateStr
import com.test.byju.viewModels.NewsHeadlinesViewModel
import kotlinx.android.synthetic.main.activity_article_details.*
import org.parceler.Parcels

class ArticleDetailsActivity : BaseActivity() {

    private lateinit var mViewModel: NewsHeadlinesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        initMembers()
    }

    private fun initMembers() {
        mViewModel = ViewModelProviders.of(this).get(NewsHeadlinesViewModel::class.java)

        details_back_button.setOnClickListener { onBackPressed() }

        intent?.let {
            val article = Parcels.unwrap<Parcelable>(intent.getParcelableExtra<Parcelable>(Constants.SELECTED_ARTICLE)) as Article?
            populateDetailsOnUi(article)
        }
    }

    private fun populateDetailsOnUi(article: Article?) {
        article?.let {
            details_imgView.setUrl(it.urlToImage)
            details_title_textView.text = it.title
            details_date_textview.text = it.publishedAt.formatToDateStr()
            detail_channel_textView.text = it.author
            detail_desc_textView.text = it.description
        }
    }
}

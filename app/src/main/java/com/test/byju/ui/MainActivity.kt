package com.test.byju.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.test.byju.R
import com.test.byju.adapter.NewsHeadlinesAdapter
import com.test.byju.io.Constants
import com.test.byju.io.dto.NewsResponse
import com.test.byju.viewModels.NewsHeadlinesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: NewsHeadlinesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMembers()
    }

    private fun initMembers() {
        mViewModel = ViewModelProviders.of(this).get(NewsHeadlinesViewModel::class.java)
        mViewModel.networksState.observe(this, Observer { handleNetworkState(it) })
        mViewModel.newsHeadLines.observe(this, Observer { handleApiResponse(it) })
        mViewModel.getHeadlines()
    }

    private fun handleApiResponse(newsResponse: NewsResponse?) {
        newsResponse?.let {
            news_recyclerView.adapter = NewsHeadlinesAdapter(this, newsResponse.articles) {

                Log.d("TAG", "news clicked")
            }
        }
    }

    private fun handleNetworkState(state: Int) {

        when(state) {
            Constants.NTWORK_STATE_NO_INTERNET -> Snackbar.make(findViewById(R.id.home_root_layout), "No Internet", Snackbar.LENGTH_LONG).show()

            Constants.NTWORK_STATE_SUCCESS -> ""

            Constants.NTWORK_STATE_ERROR -> Snackbar.make(findViewById(R.id.home_root_layout), "Something went wrong!", Snackbar.LENGTH_LONG).show()

            Constants.NTWORK_STATE_LOADING -> ""

            Constants.NTWORK_STATE_DEFAULT -> ""
        }


    }
}

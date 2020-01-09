package com.test.byju.io

import android.content.Context
import android.net.ConnectivityManager
import com.test.byju.ByjuApplication
import com.test.byju.io.dto.NewsResponse
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Manager handles all network operations like, fetching data using APIs
 */
object NetworkIoManager {

    private var service: RetroService? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getHttpClient())
            .build()

        service = retrofit!!.create(RetroService::class.java)
    }

    private fun getHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        httpClient.readTimeout(10, TimeUnit.SECONDS)
        httpClient.addNetworkInterceptor(logging)

        return httpClient.build()
    }

    fun fetchHeadlines(date: String): Single<NewsResponse> {
        return service!!.newsHeadlines(from = "2020-01-08")
    }


    fun isInternetAvailable (): Boolean {
        val connectivityManager = ByjuApplication.INSTANT.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return  networkInfo != null && networkInfo.isConnected
    }
}
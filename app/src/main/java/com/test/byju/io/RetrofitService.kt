package com.test.byju.io

import com.test.byju.io.dto.NewsResponse
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("everything")
    fun newsHeadlines(@Query("q") question: String = "headlines",
                      @Query("from") from: String,
                      @Query("apiKey") apiKey: String = Constants.API_KEY): Single<NewsResponse>
}
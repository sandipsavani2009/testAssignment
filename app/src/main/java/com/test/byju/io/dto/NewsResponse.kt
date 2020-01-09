package com.test.byju.io.dto

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

data class NewsResponse (

    @SerializedName("status")
    val status: String = "",

    @SerializedName("totalResults")
    val totalResults: Int = 0,

    @SerializedName("articles")
    val articles: List<Article>? = null
)

@Parcel
data class Article(

    @SerializedName("source")
    val source: Source? = null,

    @SerializedName("author")
    val author: String = "",

    @SerializedName("title")
    val title: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("url")
    val url: String = "",

    @SerializedName("urlToImage")
    val urlToImage: String = "",

    @SerializedName("publishedAt")
    val publishedAt: String = "",

    @SerializedName("content")
    val content: String = ""
)

@Parcel
data class Source (

    @SerializedName("id")
    val id: String = "",

    @SerializedName("name")
    val name: String = ""
)
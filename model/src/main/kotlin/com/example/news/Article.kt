package com.example.news

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    @SerializedName("source")
    val source: Source,

    @SerializedName("author")
    val author: String? = null,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("urlToImage")
    val urlToImage: String? = null,

    @SerializedName("content")
    val content: String,
): Parcelable

package com.example.newsapp

import java.io.Serializable

data class News(
    val title : String,
    val description: String,
    val imageUrl: String,
    val content: String,
    val author: String,
    val source: String,
    val lastUpdate: String,
    val newsUrl: String
) : Serializable
package com.dicoding.asclepius.data

data class NewsResponse(
    val articles: List<Articles>
)

data class Articles(
    val title: String,
    val urlToImage: String,
    val description: String,
    val url: String
)
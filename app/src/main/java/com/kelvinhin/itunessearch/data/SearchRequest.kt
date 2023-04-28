package com.kelvinhin.itunessearch.data

data class SearchRequest(
    val term: String,
    val country: String,
    val media: String?,
    val entity: String?,
    val offset: Int?,
    val limit: Int?
)

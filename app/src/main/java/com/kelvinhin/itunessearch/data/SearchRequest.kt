package com.kelvinhin.itunessearch.data

import com.kelvinhin.itunessearch.constants.MediaTypes

data class SearchRequest(
    val term: String,
    val country: String,
    val media: String? = MediaTypes.MUSIC,
    val entity: String? = null,
    val offset: Int? = 0,
    val limit: Int? = 20
)

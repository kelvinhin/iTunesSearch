package com.kelvinhin.itunessearch.data

import com.kelvinhin.itunessearch.constants.MediaTypes

data class SearchRequest(
    var term: String,
    var country: String,
    var media: String? = MediaTypes.MUSIC,
    var entity: String? = null,
    var offset: Int? = 0,
    var limit: Int? = 20
)

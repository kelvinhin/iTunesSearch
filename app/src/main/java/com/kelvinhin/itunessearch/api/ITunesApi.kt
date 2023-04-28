package com.kelvinhin.itunessearch.api

import com.kelvinhin.itunessearch.constants.RequestParams
import com.kelvinhin.itunessearch.constants.Url
import com.kelvinhin.itunessearch.data.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApi {
    @GET(Url.PATH)
    suspend fun doSearch(
        @Query(RequestParams.TERM) term: String,
        @Query(RequestParams.COUNTRY) country: String,
        @Query(RequestParams.MEDIA) media: String?,
        @Query(RequestParams.ENTITY) entity: String?,
        @Query(RequestParams.OFFSET) offset: Int?,
        @Query(RequestParams.LIMIT) limit: Int?
    ): SearchResult
}
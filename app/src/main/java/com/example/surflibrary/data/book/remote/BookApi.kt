package com.example.surflibrary.data.book.remote

import com.example.surflibrary.data.book.remote.BookUrls.SEARCH_BY_ID
import com.example.surflibrary.data.book.remote.BookUrls.VOLUMES
import com.example.surflibrary.data.book.remote.models.BookItem
import com.example.surflibrary.data.book.remote.models.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApi {

    @GET(VOLUMES)
    suspend fun getAllBooks(
        @Query("q") query: String = "Harry",
        @Query("maxResults") maxResults: Int = 20,
        @Query("startIndex") startIndex: Int = 0
    ): Response<BookResponse>

    @GET(SEARCH_BY_ID)
    suspend fun getBookById(
        @Path("id") id: Int
    ): Response<BookItem>
}
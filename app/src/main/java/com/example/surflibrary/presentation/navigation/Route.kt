package com.example.surflibrary.presentation.navigation

sealed class Route {

    data class MainScreen(val route: String = "main") : Route()

    data class BookDetails(
        val route: String = "book_details",
        val routeWithArgs: String = "$route/{$BOOK_ID}"
    ) : Route() {

        fun getRouteWithArgs(bookId: Int): String = "$route/$bookId"

        companion object {
            const val BOOK_ID = "bookId"
        }
    }

}
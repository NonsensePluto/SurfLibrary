package com.example.surflibrary.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.surflibrary.presentation.screens.details.BooksDetailScreen
import com.example.surflibrary.presentation.screens.main.MainScreen

import com.example.surflibrary.presentation.screens.favorites.FavoritesScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Route.MainScreen().route,
        modifier = modifier.fillMaxSize()
    ) {

        composable(
            route = Route.MainScreen().route
        ) {
            MainScreen(
                modifier = modifier,
                onBookClick = { bookId ->
                    navController.navigate(
                        Route.BookDetails().getRouteWithArgs(bookId)
                    ) {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = false
                        }
                    }
                },
                onNavigateToFavorites = { navController.navigate(
                    Route.FavoritesScreen().route
                )
                }
            )
        }

        composable(
            route = Route.BookDetails().routeWithArgs,
            arguments = listOf(navArgument(name = Route.BookDetails.BOOK_ID) {
                type = NavType.StringType
            })
        ) {
            BooksDetailScreen(
                modifier = modifier,
                onNavigateUp = {navController.navigateUp()},
            )
        }

        composable(
            route = Route.FavoritesScreen().route
        ) {
            FavoritesScreen(
                modifier = modifier,
                onBookClick = { bookId ->
                    navController.navigate(
                        Route.BookDetails().getRouteWithArgs(bookId)
                    ) {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = false
                        }
                    }
                },
                onNavigateToSearch = { navController.navigate(
                    Route.MainScreen().route
                )
                }
            )
        }
    }
}
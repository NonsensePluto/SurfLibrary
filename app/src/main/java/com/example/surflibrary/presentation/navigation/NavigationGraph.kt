package com.example.surflibrary.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.surflibrary.presentation.screens.main.MainScreen

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
            MainScreen(modifier = modifier)
        }
        composable(
            route = Route.BookDetails().routeWithArgs,
            arguments = listOf(navArgument(name = Route.BookDetails.BOOK_ID) {
                type = NavType.IntType
            })
        ) {
            //BookDetailScreen
        }
    }
}
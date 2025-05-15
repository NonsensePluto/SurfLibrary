package com.example.surflibrary.presentation.screens.navigation_menu

import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.surflibrary.presentation.screens.books.BookItem
import com.example.surflibrary.presentation.screens.utils.Screens

@Composable
fun BottomNavigationBar(
    currentScreen: Screens,
    onNavigateToSearch: () -> Unit,
    onNavigateToFavorites: () -> Unit,
){
    Row (
        modifier = Modifier.fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        BottomItem(
            icon = Icons.Default.Search,
            text = "Поиск",
            isSelected = currentScreen == Screens.SEARCH,
            onClick = { onNavigateToSearch() }
        )

        BottomItem(
            icon = Icons.Default.FavoriteBorder,
            text = "Избранное",
            isSelected = currentScreen == Screens.FAVORITES,
            onClick = { onNavigateToFavorites() }
        )
    }
}
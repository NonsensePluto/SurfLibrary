package com.example.surflibrary.presentation.screens.navigation_menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BottomItem(modifier: Modifier = Modifier, icon: ImageVector, text: String, onClick: () -> Unit, isSelected: Boolean = false) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon,
            contentDescription = "Icon content",
            tint = if(isSelected) Color.Blue else Color.LightGray
        )
        Text(
            text = text,
            color = if(isSelected) Color.Blue else Color.LightGray
        )
    }
}
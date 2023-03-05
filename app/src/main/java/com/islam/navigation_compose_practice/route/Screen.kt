package com.islam.navigation_compose_practice.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, icon: ImageVector) {
    object Home : Screen(route = "home_screen", title = "HomeScreen", icon = Icons.Default.Menu)
    object Details : Screen(route = "details_screen", title = "DetailsScreen", icon = Icons.Default.ArrowBack)
}

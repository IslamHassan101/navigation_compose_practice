package com.islam.navigation_compose_practice.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.islam.navigation_compose_practice.DetailsScreen
import com.islam.navigation_compose_practice.HomeScreen
import com.islam.navigation_compose_practice.route.Screen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Details.route) {
            DetailsScreen(navController = navController)
        }
    }
}
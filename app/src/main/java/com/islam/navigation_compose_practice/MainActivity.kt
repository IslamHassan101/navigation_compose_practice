package com.islam.navigation_compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.islam.navigation_compose_practice.navigation.NavGraph
import com.islam.navigation_compose_practice.route.Screen
import com.islam.navigation_compose_practice.ui.theme.Navigation_compose_practiceTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val item = listOf(Screen.Home, Screen.Details)
            Navigation_compose_practiceTheme {
                Scaffold(topBar = {
                    item.forEach { screen ->
                        TopAppBar(
                            title = { Text(text = screen.title) },
                        )
                    }

                }) {
                    navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}

package com.islam.navigation_compose_practice.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.islam.navigation_compose_practice.DetailsScreen
import com.islam.navigation_compose_practice.HomeScreen
import com.islam.navigation_compose_practice.route.Screens
import kotlinx.coroutines.launch


@Composable
fun HomeTopBar(
    currentScreens: Screens,
    navigateUp: () -> Unit,
    canNavigate: Boolean,
    openMenu: () -> Unit
) {
    TopAppBar(
        title = { Text(text = currentScreens.title) },
        navigationIcon = {
            if (canNavigate) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            } else {
                IconButton(onClick = openMenu) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                }
            }
        }
    )
}


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    // TODO{WE USE THIS FUNs to know what is the current destination we have}
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreens = Screens.valueOf(backStackEntry?.destination?.route ?: Screens.Home.name)
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeTopBar(
                currentScreens = currentScreens,
                navigateUp = {
                    navController.navigateUp()
                },
                canNavigate = navController.previousBackStackEntry != null,
                openMenu = {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            )
        },
        drawerContent = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.clickable {
                        navController.navigate(route = Screens.Profile.name)
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    },
                    text = "Profile"
                )
                Text(
                    modifier = Modifier.clickable {
                        navController.navigate(route = Screens.Setting.name)
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    },
                    text = "Settings"
                )
            }
        },
    ) {

        NavHost(navController = navController, startDestination = Screens.Home.name) {
            composable(route = Screens.Home.name) {
                HomeScreen(onHomeButtonClicked = {
                    navController.navigate(route = Screens.Details.name)
                })
            }

            composable(route = Screens.Details.name) {
                DetailsScreen(onDetailsButtonClicked = { navController.navigate(route = Screens.Setting.name) })
            }

            composable(route = Screens.Setting.name) {
                SettingScreen(onSettingsButtonClicked = {
                    //TODO{NO ACTION NEED!}
                })
            }
            composable(route = Screens.Profile.name) {
                ProfileScreen(onProfileButtonClicked = { navController.popBackStack() })
            }
        }
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}
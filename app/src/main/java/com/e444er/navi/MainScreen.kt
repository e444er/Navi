package com.e444er.navi

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavController) {

    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Chat,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    BottomNavigation {
        screens.forEach { screen ->
            BottomNavigationItem(
                label = {
                    Text(text = screen.title)
                },
                icon = {
                    Icon(imageVector = screen.icon, contentDescription = null)
                },
                selected = currentDestination == screen.route,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

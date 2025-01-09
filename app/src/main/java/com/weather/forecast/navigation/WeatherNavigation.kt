package com.weather.forecast.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.weather.forecast.R
import com.weather.forecast.screens.about.AboutScreen
import com.weather.forecast.screens.main.MainScreen
import com.weather.forecast.screens.main.MainViewModel
import com.weather.forecast.screens.search.SearchScreen
import com.weather.forecast.screens.splash.SplashScreen

@Composable
fun WeatherNavigation() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        WeatherScreens.MainScreen.name -> 0
        WeatherScreens.SearchScreen.name -> 1
        WeatherScreens.AboutScreen.name -> 2
        else -> 0
    }
    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route?.contains(WeatherScreens.MainScreen.name) == true ||
                backStackState?.destination?.route == WeatherScreens.SearchScreen.name ||
                backStackState?.destination?.route == WeatherScreens.AboutScreen.name
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            WeatherBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = WeatherScreens.MainScreen.name
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = WeatherScreens.SearchScreen.name
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = WeatherScreens.AboutScreen.name
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = WeatherScreens.MainScreen.name,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(WeatherScreens.SplashScreen.name) {
                SplashScreen(navController = navController)
            }
//            //www.google.com/location="seattle"
//            val route = WeatherScreens.MainScreen.name
//            composable(
//                "$route/{location}",
//                arguments = listOf(
//                    navArgument(name = "location") {
//                        type = NavType.StringType
//                    })
//            ) { navBack ->
//                navBack.arguments?.getString("location").let { location ->
//                    val mainViewModel = hiltViewModel<MainViewModel>()
//                    MainScreen(
//                        navController = navController, mainViewModel,
//                        location = location
//                    )
//                }
//            }
            composable(
                WeatherScreens.MainScreen.name
            ) {
                // Handle back button press when on the main video screen
                BackHandler(true) {}
                MainScreen(
                    navController = navController,
                    hiltViewModel<MainViewModel>(),
                    location = "101020100"
                )
            }
            composable(WeatherScreens.SearchScreen.name) {
                SearchScreen(navController = navController)
            }
            composable(WeatherScreens.AboutScreen.name) {
                AboutScreen(navController = navController)
            }
        }
    }
}


private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
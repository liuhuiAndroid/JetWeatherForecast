package com.weather.forecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.weather.forecast.screens.about.AboutScreen
import com.weather.forecast.screens.main.MainViewModel
import com.weather.forecast.screens.main.MainScreen
import com.weather.forecast.screens.splash.SplashScreen
import com.weather.forecast.screens.search.SearchScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(WeatherScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        //www.google.com/location="seattle"
        val route = WeatherScreens.MainScreen.name
        composable(
            "$route/{location}",
            arguments = listOf(
                navArgument(name = "location") {
                    type = NavType.StringType
                })
        ) { navBack ->
            navBack.arguments?.getString("location").let { location ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    navController = navController, mainViewModel,
                    location = location
                )
            }
        }
        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }
    }
}
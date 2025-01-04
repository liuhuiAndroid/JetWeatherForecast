package com.weather.forecast.screens.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.weather.forecast.navigation.WeatherScreens

@Composable
fun SearchScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(330.dp),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(
            width = 2.dp, color = Color.LightGray
        )
    ) {
        Column(
            modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Search back",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.LightGray,
                modifier = Modifier.clickable {
//                    navController.previousBackStackEntry?.savedStateHandle?.set("ResultData", 66666)
                    navController.navigate(
                        route = "${WeatherScreens.MainScreen.name}/100002",
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(
                                WeatherScreens.SearchScreen.name,
                                inclusive = true
                            ) // 销毁当前页面
                            .setLaunchSingleTop(true) // 确保 MainScreen 只有一个实例
                            .build()
                    )
                }
            )
        }
    }
}
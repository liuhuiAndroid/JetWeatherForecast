package com.weather.forecast.screens.main

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.weather.forecast.data.DataOrException
import com.weather.forecast.model.WeatherData
import com.weather.forecast.navigation.WeatherScreens
import com.weather.forecast.widgets.HumidityWindPressureRow
import com.weather.forecast.widgets.SunsetSunRiseRow
import com.weather.forecast.widgets.WeatherAppBar
import com.weather.forecast.widgets.WeatherDetailRow

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    location: String?,
) {
    val currentLocation: String =
        if (location?.isBlank() == true) "101010100" else location.toString()
    val weatherData = produceState<DataOrException<WeatherData, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(
            location = currentLocation,
        )
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(
            navController = navController,
            mainViewModel = mainViewModel,
            weatherData = weatherData.data!!
        )
    }
}

@Composable
fun MainScaffold(
    navController: NavController,
    mainViewModel: MainViewModel,
    weatherData: WeatherData,
) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = weatherData.weatherNow.now.obsTime,
            navController = navController,
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            },
            elevation = 5.dp
        ) {
            Log.d("TAG", "MainScaffold: Button Clicked")
        }
    }) { padding ->
        MainContent(
            modifier = Modifier.padding(padding),
            navController = navController,
            mainViewModel = mainViewModel,
            weatherData = weatherData
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavController,
    mainViewModel: MainViewModel,
    weatherData: WeatherData,
) {
    Column(
        modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weatherData.weatherNow.updateTime,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )
        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${weatherData.weatherNow.now.text}º",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "${weatherData.weatherNow.now.temp}º",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = weatherData.weatherNow.now.windDir,
                    fontStyle = FontStyle.Italic
                )
            }
        }
        HumidityWindPressureRow(weather = weatherData.weatherNow)
        HorizontalDivider()
        SunsetSunRiseRow(daily = weatherData.weather7d.daily.get(0))
        Text(
            "This Week",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable {
                    navController.navigate(WeatherScreens.AboutScreen.name)
                },
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(
                    count = weatherData.weather7d.daily.size,
                    itemContent = { index ->
                        val item = weatherData.weather7d.daily[index]
                        WeatherDetailRow(daily = item)
                    }
                )
            }
        }
    }
}

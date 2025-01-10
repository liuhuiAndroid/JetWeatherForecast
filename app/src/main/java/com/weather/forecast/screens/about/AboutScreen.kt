package com.weather.forecast.screens.about

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.weather.forecast.R
import com.weather.forecast.widgets.CustomDialog
import com.weather.forecast.widgets.CustomLottieAnimation
import com.weather.forecast.widgets.ExpandableText
import com.weather.forecast.widgets.WeatherTopAppBar

@Composable
fun AboutScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        WeatherTopAppBar(
            title = "About", true, navController = navController
        ) {
            navController.popBackStack()
        }
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.about_app),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        showDialog = true
                    })
                Text(
                    text = stringResource(id = R.string.api_used), fontWeight = FontWeight.Light
                )
                ExpandableText(
                    fontSize = 12.sp,
                    text = stringResource(id = R.string.about_info),
                    modifier = Modifier.padding(3.dp)
                )
                CustomLottieAnimation()
                CustomDialog(showDialog = showDialog,
                    title = "确认操作",
                    message = "你确定要执行这个操作吗？",
                    confirmButtonText = "确认",
                    cancelButtonText = "取消",
                    onConfirm = { /* 确认逻辑 */ },
                    onDismiss = { showDialog = false })
            }
        }
    }

}

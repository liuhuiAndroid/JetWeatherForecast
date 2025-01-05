# Navigation

navController.navigate(WeatherScreens.MainScreen.name + "/$defaultCity")
navController.popBackStack()
navController.navigate(WeatherScreens.MainScreen.name + "/$mCity")

composable(
    "${WeatherScreens.MainScreen.name}/{city}",
    arguments = listOf(
        navArgument(name = "city") {
            type = NavType.StringType
        })
) { navBack ->
    navBack.arguments?.getString("city").let { city ->
        val mainViewModel = hiltViewModel<MainViewModel>()
        MainScreen(
            navController = navController, mainViewModel,
            city = city
        )
    }
}

// 使用 NavController 导航与 NavOptions 设置
navController.navigate(
    route = "${WeatherScreens.MainScreen.name}/100002",
    navOptions = NavOptions.Builder()
        .setPopUpTo(WeatherScreens.SearchScreen.name, inclusive = true) // 销毁当前页面
        .setLaunchSingleTop(true) // 确保 MainScreen 只有一个实例
        .build()
)

// 从目标页面返回到上一个页面
页面 B 中保存数据：
navController.previousBackStackEntry?.savedStateHandle?.set("ResultData", 66666)
页面 A 中获取数据：
val resultData = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("ResultData")

package com.udemy.navigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.udemy.navigationsample.ui.theme.NavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.FirstScreen.route) {
        composable(route = Screen.FirstScreen.route) {
            FirstScreen(modifier) { name, age, optional ->
                println("$name $age")
                navController.navigate(Screen.SecondScreen.route + "/$name/$age?optionalArg=$optional")
            }
        }
        composable(route = Screen.SecondScreen.route + "/{name}/{age}?optionalArg={optionalArg}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("age") { type = NavType.IntType },
                navArgument("optionalArg") {
                    type = NavType.StringType
                    defaultValue = "default"
                    nullable = true
                }
            )) {
            val name = it.arguments?.getString("name") ?: "no name"
            val age = it.arguments?.getInt("age") ?: 0
            val optional = it.arguments?.getString("optionalArg") ?: "no optional arg"
            println("$name $age $optional")
            SecondScreen(
                modifier,
                name,
                age,
                navigateToThirdScreen = { dataName, dataAge ->
                    val data = DataObject(dataName, dataAge)
                    val dataGson = Gson().toJson(data)
                    navController.navigate(Screen.ThirdScreen.route + "/$dataGson")
                },
                navigateToFourthScreen = { data ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("data", data)
                    navController.navigate(Screen.FourthScreen.route)
                }
            )
        }
        composable(route = Screen.ThirdScreen.route + "/{data}") {
            val dataGson = it.arguments?.getString("data") ?: ""
            val data = Gson().fromJson(dataGson, DataObject::class.java)
            ThirdScreen(modifier, data) {
                navController.navigate(Screen.FirstScreen.route)
            }
        }
        composable(route = Screen.FourthScreen.route) {
            val data: DataObject =
                navController.previousBackStackEntry?.savedStateHandle?.get<DataObject>("data")
                    ?: DataObject("", 0)
            FourthScreen(modifier, data = data) {
                navController.navigate(Screen.FirstScreen.route)
            }
        }
    }
}

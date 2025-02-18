package com.udemy.navigationsample

sealed class Screen(val route: String) {
    data object FirstScreen: Screen("first_screen")
    data object SecondScreen: Screen("second_screen")
    data object ThirdScreen: Screen("third_screen")
    data object FourthScreen: Screen("fourth_screen")
}

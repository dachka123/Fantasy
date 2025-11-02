package com.example.fantastika.Navigation

sealed class NavRoutes(val route: String) {
    object Landing : NavRoutes("landing")

    object MainApp : NavRoutes("mainApp")
}
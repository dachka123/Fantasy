package com.example.fantastika.Navigation

sealed class NavRoutes(val route: String) {
    object Landing : NavRoutes("landing")

    object MainApp : NavRoutes("mainApp")

    object Login : NavRoutes("login")
    object Register : NavRoutes("register")

    object Leaderboard : NavRoutes("leaderboard")
    object Statistics : NavRoutes("statistics")
}
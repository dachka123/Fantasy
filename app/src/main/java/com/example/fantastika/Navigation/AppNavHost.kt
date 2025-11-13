package com.example.fantastika.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fantastika.LandingPage.MainLandingPage
import com.example.fantastika.LoginRegister.Login.LoginPageContent
import com.example.fantastika.LoginRegister.Register.RegisterPageContent
import com.example.fantastika.PlayerSelection.PlayerSelectionSideBar.SideBarViewModel
import com.example.fantastika.SideBarDragDrop

@Composable
fun AppNavHost(
    navController: NavHostController,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    startDestination: String = NavRoutes.Landing.route
) {
    val mainViewModel: SideBarViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavRoutes.Landing.route) {
            MainLandingPage(
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated,

                onStartApp = {
                    navController.navigate(NavRoutes.MainApp.route)
                },
                onNavigateToFixtures = {
                    navController.navigate(NavRoutes.MainApp.route)
                },
                onNavigateToLogin = {
                    navController.navigate(NavRoutes.Login.route)
                }
            )
        }

        composable(NavRoutes.MainApp.route) {
            SideBarDragDrop(
                viewModel = mainViewModel,
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.Login.route) {
            LoginPageContent(
                onNavigateToRegister = {
                    navController.navigate(NavRoutes.Register.route)
                }
            )
        }

        composable(NavRoutes.Register.route) {
            RegisterPageContent()
        }
    }
}
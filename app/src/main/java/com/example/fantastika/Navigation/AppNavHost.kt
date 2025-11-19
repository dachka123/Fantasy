package com.example.fantastika.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fantastika.LandingPage.MainLandingPage
import com.example.fantastika.LoginRegister.Presentation.Login.LoginPageContent
import com.example.fantastika.LoginRegister.Presentation.Register.RegisterPageContent
import com.example.fantastika.PlayerSelection.Presentation.PlayerSelectionSideBar.SideBarViewModel
import com.example.fantastika.SideBarDragDrop

@Composable
fun AppNavHost(
    navController: NavHostController,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    startDestination: String = NavRoutes.Landing.route
) {

    var accessToken by remember { mutableStateOf<String?>(null) }
    var loggedInUsername by remember { mutableStateOf<String?>(null) }
    var loggedInUserId by remember { mutableStateOf<String?>(null) }

    val onLogout: () -> Unit = {
        accessToken = null
        loggedInUsername = null
        navController.navigate(NavRoutes.Landing.route) {
            popUpTo(navController.graph.startDestinationId) { inclusive = false }
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavRoutes.Landing.route) {
            MainLandingPage(
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated,
                isUserLoggedIn = accessToken != null,
                loggedInUsername = loggedInUsername,
                onLogout = onLogout,

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
            val sideBarViewModel: SideBarViewModel = hiltViewModel()
            SideBarDragDrop(
                viewModel = sideBarViewModel,
                darkTheme = darkTheme,
                loggedInUserId = loggedInUserId,
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
                },
                onLoginSuccess = { token, username ->
                    accessToken = token
                    loggedInUsername = username // Store the username on successful login
                    navController.navigate(NavRoutes.Landing.route) {
                        popUpTo(NavRoutes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.Register.route) {
            RegisterPageContent(
                // Pass navigation to automatically go back to Login on successful registration
                onRegistrationSuccess = {
                    navController.navigate(NavRoutes.Login.route) {
                        popUpTo(NavRoutes.Register.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
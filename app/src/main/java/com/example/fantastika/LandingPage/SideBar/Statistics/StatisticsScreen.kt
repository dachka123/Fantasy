package com.example.fantastika.LandingPage.SideBar.Statistics

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fantastika.LandingPage.LandingScreenWrapper

@Composable
fun StatisticsScreen(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false,
    onThemeUpdated: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToLeaderboard: () -> Unit = {},
    onNavigateToStatistics: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
    isUserLoggedIn: Boolean = false,
    loggedInUsername: String? = null,
    onLogout: () -> Unit = {},
    onNavigateToFixtures: () -> Unit
) {
    LandingScreenWrapper(
        darkTheme = darkTheme,
        onThemeUpdated = onThemeUpdated,
        isUserLoggedIn = isUserLoggedIn,
        loggedInUsername = loggedInUsername,
        onLogout = onLogout,
        onNavigateToHome = onNavigateToHome,
        onNavigateToLeaderboard = onNavigateToLeaderboard,
        onNavigateToStatistics = onNavigateToStatistics,
        onNavigateToLogin = onNavigateToLogin,
        onNavigateToFixtures = onNavigateToFixtures
    ) { padding ->
        // Your statistics content here
        Text(
            text = "Statistics Content",
            modifier = Modifier.padding(padding)
        )
    }
}
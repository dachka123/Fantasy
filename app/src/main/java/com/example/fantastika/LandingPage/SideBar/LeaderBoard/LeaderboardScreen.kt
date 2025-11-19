package com.example.fantastika.LandingPage.SideBar.LeaderBoard

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fantastika.LandingPage.LandingScreenWrapper

@Composable
fun LeaderboardScreen(
    modifier: Modifier = Modifier,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    isUserLoggedIn: Boolean,
    loggedInUsername: String?,
    onLogout: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToLeaderboard: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    onNavigateToLogin: () -> Unit,
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
        Text(
            text = "Leaderboard Content",
            modifier = Modifier.padding(padding)
        )
    }
}
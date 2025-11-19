package com.example.fantastika.LandingPage

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.fantastika.LandingPage.Components.FixtureBoxes
@Composable
fun MainLandingPage(
    onStartApp: () -> Unit,
    onNavigateToFixtures: () -> Unit,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToLeaderboard: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    isUserLoggedIn: Boolean,
    loggedInUsername: String?,
    onLogout: () -> Unit,
) {
    val context = LocalContext.current

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
        FixtureBoxes(
            onClick = {
                if (isUserLoggedIn) {
                    onNavigateToFixtures()
                } else {
                    Toast.makeText(
                        context,
                        "First login if you want to select players or see a team",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }
}